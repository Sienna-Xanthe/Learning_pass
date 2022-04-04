package cn.race.oss.controller;

import cn.race.common.response.Result;
import cn.race.oss.service.FileService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class OssController {
    @Autowired
    private FileService fileService;

    /*
     * 文件上传api
     * @param: file
     * @return: com.alibaba.fastjson.JSONObject
     * @create: 2020/10/31 17:35
     * @author: csp1999
     */

    @PostMapping("/oss/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {

        if (file != null) {
            String returnFileUrl = fileService.upload(file);
            if (returnFileUrl.equals("error")) {
                return Result.fail("文件上传失败！");
            }
            return Result.succ("文件上传成功",returnFileUrl);
        } else {
            return Result.fail("文件上传失败！");
        }
    }

}
