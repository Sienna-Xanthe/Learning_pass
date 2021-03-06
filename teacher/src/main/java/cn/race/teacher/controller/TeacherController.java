package cn.race.teacher.controller;

import cn.race.common.MD5.MD5Util;
import cn.race.common.response.BusinessException;
import cn.race.common.response.CommonErrorCode;
import cn.race.common.response.Result;
import cn.race.teacher.dto.QsTotalDto;
import cn.race.teacher.dto.TsDisDto;
import cn.race.teacher.dto.TsPaperDto;
import cn.race.teacher.pojo.*;
import cn.race.teacher.service.*;
import cn.race.teacher.service.impl.MailService;
import cn.race.teacher.util.ConstantVodUtils;
import cn.race.teacher.util.JWTUtils;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher/")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TeacherController {
    @Autowired
    private SysUserService sysUserService;


    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    ITsPaperService iTsPaperService;

    @Autowired
    ITsDisService tsDisService;

    @Autowired
    ITsDetailsService iTsDetailsService;

    @Autowired
    IQsTotalService iQsTotalService;

    @Autowired
    MailService mailService;

    @Autowired
    IQsOpService iQsOpService;

    @Autowired
    IStudentService iStudentService;

    @Autowired
    ITsPublicService iTsPublicService;

    @Autowired
    ITsAnsService iTsAnsService;

    @Autowired
    IStdAnsService iStdAnsService;

    @Autowired
    IAnsDetailsService ansDetailsService;

    @Autowired
    FileService fileService;

    @Autowired
    VodService vodService;

    /**
     * ????????????
     * @param
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestParam String username, @RequestParam String password, @RequestParam String role) {

        if(StringUtils.isBlank(username)){
            throw new BusinessException(CommonErrorCode.NAME_NULL);
        }
        if(StringUtils.isBlank(password)){
            throw new BusinessException(CommonErrorCode.PASSWORD_NULL);
        }
        if(StringUtils.isBlank(role)){
            throw new BusinessException(CommonErrorCode.ROLE_NULL);
        }

        SysUser sysUser = sysUserService.login(username, password);
        List<SysRole> sysRoles = sysUser.getSysRoles();
        for(SysRole sysRole:sysRoles){
            if(sysRole.getCode().equals(role)&& ("teacher".equals(role) || "admin".equals(role))){
                Map<String, String> map1 = new HashMap<>();
                map1.put("id", sysUser.getId().toString());
                map1.put("username", sysUser.getUsername());
                map1.put("avatar",sysUser.getAvatar());
                map1.put("phone",sysUser.getPhone());
                map1.put("email",sysUser.getEmail());
                //??????token??????
                String token = JWTUtils.getToken(map1);
                Map<String, Object> maps = new HashMap<>();
                maps.put("token",token);
                maps.put("info",map1);
                return Result.succ("????????????",maps);
            }
        }
        return Result.fail("????????????");
    }

    /**
     * ????????????
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) {

        if (file != null) {
            String returnFileUrl = fileService.upload(file);
            if (returnFileUrl.equals("error")) {
                return Result.fail("?????????????????????");
            }
            return Result.succ("??????????????????",returnFileUrl);
        } else {
            return Result.fail("?????????????????????");
        }
    }



    @PostMapping("uploadAlyVideo")
    public Result uploadAlyVideo(@RequestParam MultipartFile file) {
        String videoId = vodService.uploadVideoAly(file);
        System.out.println(videoId);
        String s = GetPlayInfo(videoId);
        return Result.succ("??????????????????",s);
    }

    /**
     * ????????????????????????
     *
     * @param vid ??????id
     */
    public static String GetPlayInfo(String vid) {
        // ??????SubmitMediaInfoJob??????????????????
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-Shanghai",                // // ???????????????????????????ID???????????????????????????cn-shanghai
                ConstantVodUtils.ACCESS_KEY_ID,        // ??????AccessKey ID
                ConstantVodUtils.ACCESS_KEY_SECRET);    // ??????AccessKey Secret
        IAcsClient client = new DefaultAcsClient(profile);
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        // ??????ID???
        request.setVideoId(vid);
        String url = null;
        String newurl = null;
        try {
            GetPlayInfoResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
            for (GetPlayInfoResponse.PlayInfo playInfo : response.getPlayInfoList()) {
                // ????????????
                System.out.println("PlayInfo.PlayURL = " + playInfo.getPlayURL());
                String str = playInfo.getPlayURL();
                //???????????????m3u8???mp4?????????m3u8??????????????????????????????
                //???????????????????????????mp4??????
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


    /**
     * ????????????
     * @param
     * @return
     */
    @PostMapping("logout")
    public Result logout(HttpServletRequest request) {
        JWTUtils.deleteToken(request);
        return Result.succ("????????????",null);
    }


    /**
     * jwt?????????????????????????????????????????????token?????????????????????
     * @param request
     * @return
     */
    @PostMapping("test")
    public Result test(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        map.put("username", verify.getClaim("username").asString());
        map.put("id",verify.getClaim("id").asString());
        return Result.succ("??????token??????",map);
    }

    /**
     * ???????????????????????????
     */
    @PostMapping("selectallqsbytype")
    public Result selectallqsbytype(@RequestParam Integer projectId) {
        Map<String, Integer> stringIntegerMap = iQsTotalService.selectQsCount(projectId);
        return Result.succ("????????????",stringIntegerMap);
    }



    /**
     * ????????????
     */
    @PostMapping("addpaper")
    public Result addpaper(@RequestBody TsPaper tsPaper, HttpServletRequest request) {
        String token = request.getHeader("token");
        String id = JWTUtils.verify(token).getClaim("id").asString();
        Integer paperId = iTsPaperService.addpaper(Integer.parseInt(id),tsPaper);
        //?????????tdDis?????????
        List<TsDis> listDis = tsPaper.getListDis();
        for(TsDis tsDis:listDis){
            tsDisService.addTsDis(paperId,tsDis);
        }
       //????????????id???????????????????????????qsId

        iTsDetailsService.addTsDetails(paperId,tsPaper.getPrId());

        //??????
        return Result.succ("????????????",paperId);
    }

    /**
     * ????????????
     */
    @PostMapping("deletepaper")
    public Result deletepaper(@RequestBody Integer[] paperIds) {
        Integer deletepaper = iTsPaperService.deletepaper(paperIds);
        return deletepaper>0?Result.succ("????????????",deletepaper):Result.fail("????????????");
    }

    /**
     * ????????????????????????(?????????????????????id)
     */
    @PostMapping("selectallpaper")
    public Result selectallpaper(@RequestParam Integer projectId,@RequestParam String tsName) {
        List<TsPaper> tsPapers = iTsPaperService.selectByPr(projectId, tsName);
        return Result.succ("????????????",tsPapers);
    }

    /**
     * ?????????????????????
     */
    @GetMapping("selectbyrecycle/{projectId}")
    public Result selectbyrecycle(@PathVariable("projectId") Integer projectId) {
        List<TsPaper> selectbyrecycle = iTsPaperService.selectbyrecycle(projectId);
        return Result.succ("????????????",selectbyrecycle);
    }

    /**
     * ?????????????????????
     */
    @PostMapping("bankbyrecycle")
    public Result bankbyrecycle(@RequestParam Integer paperId) {
        iTsPaperService.bankbyrecycle(paperId);
        return Result.succ("????????????",null);
    }

    /**
     * ????????????
     */
    @PostMapping("sealpaper")
    public Result sealpaper(@RequestParam Integer paperId,@RequestParam String password,@RequestParam String comfirePassword) {
        if (StringUtils.isBlank(password) || StringUtils.isBlank(comfirePassword)) {
            throw new BusinessException(CommonErrorCode.PASSWORD_NULL);
        }
        if(!password.equals(comfirePassword)){
            throw new BusinessException(CommonErrorCode.PASSWORD_NO);
        }
        Integer sealpaper = iTsPaperService.sealpaper(paperId, password);
        return sealpaper>0?Result.succ("????????????",sealpaper):Result.fail("????????????");
    }

    /**
     * ????????????
     */
    @PostMapping("nosealpaper")
    public Result nosealpaper(@RequestParam Integer paperId,@RequestParam String password) {
         iTsPaperService.nosealpaper(paperId,password);
         return Result.succ("????????????",null);
    }






    /**
     * ??????????????????????????????
     */
    @PostMapping("forgetpass")
    public Result forgetpass(HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            String id = JWTUtils.verify(token).getClaim("id").asString();
            //??????id????????????
            String mail = sysUserService.selectMail(Integer.parseInt(id));
            mailService.sendHtmlMail(mail, "?????????????????????????????????", "<h1>http://localhost:10010/teacher/repassword</h1>");
            return Result.succ("????????????",null);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("????????????");
        }
    }

    /**
     * ????????????
     */
    @GetMapping("repassword")
    public Result forgetpass(Integer paperId,String newPassword) {
        Integer forgetpass = iTsPaperService.forgetpass(paperId, newPassword);
        return forgetpass>0?Result.succ("????????????",forgetpass):Result.fail("????????????");
    }

    /**
     *??????????????????
     */
    @GetMapping("selectpaperdetail")
    public Result selectpaperdetail(Integer paperId,String password) {

        String s = iTsPaperService.selectPassword(paperId);

        if(StringUtils.isNotBlank(s) && !MD5Util.encode(password).equals(s)){
            throw new BusinessException(CommonErrorCode.PASSWORD_NO);
        }

        if(StringUtils.isNotBlank(s) && StringUtils.isBlank(password)){
            throw new BusinessException(CommonErrorCode.PASSWORD_NULL);
        }

        HashMap<String, Object> map = new HashMap<>();
        TsPaperDto tsPaperDto = iTsPaperService.selectById(paperId);
        map.put("tsPaper",tsPaperDto);
        List<TsDisDto> selectpaperdetail = tsDisService.selectpaperdetail(paperId);
        map.put("tsDis",selectpaperdetail);
        List<TsDetails> selectpaperdetail1 = iTsDetailsService.selectpaperdetail(paperId);
        map.put("tsDetails",selectpaperdetail1);
        //??????qsId??????
        List<QsTotalDto> qsTotalDtoList = new ArrayList<>();
        List<List<QsOp>> opList1 = new ArrayList<>();
        for(TsDetails ts:selectpaperdetail1){
            Integer qsId = ts.getQsId();
            QsTotalDto qsTotalDto = iQsTotalService.selectpaperdetail(qsId);
           qsTotalDtoList.add(qsTotalDto);
            List<QsOp> qsOpList = iQsOpService.selectpaperdetail(qsId);
            opList1.add(qsOpList);
        }
        map.put("qsTotal",qsTotalDtoList);
        map.put("qsOp",opList1);
        return Result.succ("????????????",map);
    }

    /**
     * ????????????????????????????????????
     */
    @GetMapping("selectstubypro")
    public Result selectstubypro( Integer proId) {
        List<Student> students = iStudentService.selectBypr(proId);
        return Result.succ("????????????",students);
    }

        /**
         * ????????????
         */
    @PostMapping("publicpaper/{paperId}")
    public Result publicpaper(@PathVariable("paperId") Integer paperId,@RequestBody TsPublic tsPublic) {
        int i = iTsPublicService.addTsPublic(tsPublic,paperId);
        List<Integer> studentList = tsPublic.getStudentList();
        for(Integer stId:studentList){
            String s = sysUserService.selectName(stId);
            iTsAnsService.addTsAns(i,stId,s);
        }
        return i>0?Result.succ("????????????",i):Result.fail("????????????");
    }

    /**
     * ???????????????????????????
     */
    @GetMapping("showreadpaper")
    public Result showreadpaper(Integer tsAnsId) {
        HashMap<String, Object> map = new HashMap<>();
        //??????pubId??????paperId
        TsAns tsAns = iTsAnsService.selectById(tsAnsId);
        Integer paperId = iTsPublicService.selectByPubId(tsAns.getPubId());
        map.put("tsAns",tsAns); //???????????????id
        List<StdAns> stdAns = iStdAnsService.selectDe(tsAns.getPubId(),tsAns.getStId());
        List<List<AnsDetails>> ansDList = new ArrayList<>();
        for(StdAns stdAns1:stdAns){
            Integer id1 = stdAns1.getId();
            List<AnsDetails> ansDetails = ansDetailsService.selectList(id1);
            ansDList.add(ansDetails);
        }
        map.put("ansDetails",ansDList);
        map.put("stdAns",stdAns);
        TsPaperDto tsPaperDto = iTsPaperService.selectById(paperId);
        map.put("tsPaper",tsPaperDto);
        List<TsDisDto> selectpaperdetail = tsDisService.selectpaperdetail(paperId);
        map.put("tsDis",selectpaperdetail);
        List<TsDetails> selectpaperdetail1 = iTsDetailsService.selectpaperdetail(paperId);
        map.put("tsDetails",selectpaperdetail1);
        //??????qsId??????
        List<QsTotalDto> qsTotalDtoList = new ArrayList<>();
        List<List<QsOp>> opList1 = new ArrayList<>();
        for(TsDetails ts:selectpaperdetail1){
            Integer qsId = ts.getQsId();
            QsTotalDto qsTotalDto = iQsTotalService.selectpaperdetail(qsId);
            qsTotalDtoList.add(qsTotalDto);
            List<QsOp> qsOpList = iQsOpService.selectpaperdetail(qsId);
            opList1.add(qsOpList);
        }
        map.put("qsTotal",qsTotalDtoList);
        map.put("qsOp",opList1);
        return Result.succ("????????????",map);
    }

    /**
     * ????????????
     */
    @PostMapping("readpaper/{tsAnsId}")
    public Result readpaper(@PathVariable("tsAnsId") Integer tsAnsId,@RequestBody TsAns tsAns){
        int i = iTsAnsService.updateTsAns(tsAnsId, tsAns);
        return Result.succ("????????????",i);
    }

}
