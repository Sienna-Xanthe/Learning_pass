package cn.race.video.controller;

import cn.race.common.response.Result;
import cn.race.video.service.VodService;
import cn.race.video.util.ConstantVodUtils;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/video/")
@CrossOrigin
public class VideoController {
    @Autowired
    VodService vodService;

    @PostMapping("uploadAlyVideo")
    public Result uploadAlyVideo(@RequestParam MultipartFile file) {
        String videoId = vodService.uploadVideoAly(file);
        System.out.println(videoId);
        String s = GetPlayInfo(videoId);
        return Result.succ("上传视频成功",s);
    }

    /**
     * 获取上传视频地址
     *
     * @param vid 视频id
     */
    public static String GetPlayInfo(String vid) {
        // 创建SubmitMediaInfoJob实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-Shanghai",                // // 点播服务所在的地域ID，中国大陆地域请填cn-shanghai
                ConstantVodUtils.ACCESS_KEY_ID,        // 您的AccessKey ID
                ConstantVodUtils.ACCESS_KEY_SECRET);    // 您的AccessKey Secret
        IAcsClient client = new DefaultAcsClient(profile);
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        // 视频ID。
        request.setVideoId(vid);
        String url = null;
        String newurl = null;
        try {
            GetPlayInfoResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
            for (GetPlayInfoResponse.PlayInfo playInfo : response.getPlayInfoList()) {
                // 播放地址
                System.out.println("PlayInfo.PlayURL = " + playInfo.getPlayURL());
                String str = playInfo.getPlayURL();
                //这里会返回m3u8和mp4格式，m3u8需要转码，看自己情况
                //如果播放地址后缀为mp4返回
                if (str != null || str != "") {
//                    if(str.substring(str.length()-3,str.length()).equals("mp4")) {
                    url = playInfo.getPlayURL();
                    int index = url.indexOf("?");
                    newurl = url.substring(0, index);
//                    }
                }
            }
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
