package cn.race.teacher.service.impl;


import cn.race.teacher.config.AliyunOssConfig;
import cn.race.teacher.service.FileService;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    //允许上传的图片格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".gif", ".png"};

    //注入阿里云oss文件服务客服端
    @Autowired
    private OSS ossclient;

    //注入阿里云OSS基本配置类
    @Autowired
    private AliyunOssConfig aliyunOssConfig;


    @Override
    public String upload(MultipartFile multipartFile) {
        //获取oss的Bucket名称
        String bucketName = aliyunOssConfig.getBucketName();
        //获取oss地域节点
        String endpoint = aliyunOssConfig.getEndPoint();
        //获取accessKeyId
        String accessKeyId = aliyunOssConfig.getAccessKeyId();
        //获取accessKeySecret
        String accessKeySecret = aliyunOssConfig.getAccessKeySecret();
        //获取oss目标文件
        String fileHost = aliyunOssConfig.getFileHost();
        //返回图片上传后返回url
        String returnImgeUrl = "";

        //校验图片格式
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(multipartFile.getOriginalFilename(), type)) {
                isLegal = true;
                break;
            }
        }
        if (!isLegal) {// 如果图片格式不合法
            throw new RuntimeException("图片格式不合法");
        }

        //获取文件原名称
        String originFileName = multipartFile.getOriginalFilename();
        //获取文件类型
        String type = originFileName.substring(originFileName.lastIndexOf("."));
//新文件名称
        String newFileName = UUID.randomUUID().toString() + type;
        //构建日期类型
        String filePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        //文件上传的路径
        String uploadImageUrl = fileHost + "/" + filePath + "/" + newFileName;

        //获取文件输入流
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 下面两行代码是重点坑：
         * 现在阿里云OSS 默认图片上传ContentType是image/jpeg
         * 也就是说，获取图片链接后，图片是下载链接，而并非在线浏览链接，
         * 因此，这里在上传的时候要解决ContentType的问题，将其改为image/jpg
         */
        ObjectMetadata meta = new ObjectMetadata();
        meta.setContentType("image/jpg");

        //文件上传至阿里云OSS
        ossclient.putObject(bucketName, uploadImageUrl, inputStream, meta);
        /**
         * 注意：在实际项目中，文件上传成功后，数据库中存储文件地址
         */
        // 获取文件上传后的图片返回地址
        returnImgeUrl = "http://" + bucketName + "." + endpoint + "/" + uploadImageUrl;

        return returnImgeUrl;
    }
}
