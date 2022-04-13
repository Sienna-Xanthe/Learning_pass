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
import cn.race.teacher.util.JWTUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 学生登录
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
            if(sysRole.getCode().equals(role)){
                Map<String, String> map1 = new HashMap<>();
                map1.put("id", sysUser.getId().toString());
                map1.put("username", sysUser.getUsername());
                map1.put("avatar",sysUser.getAvatar());
                map1.put("phone",sysUser.getPhone());
                map1.put("email",sysUser.getEmail());
                //生成token令牌
                String token = JWTUtils.getToken(map1);
                Map<String, Object> maps = new HashMap<>();
                maps.put("token",token);
                maps.put("info",map1);
                return Result.succ("登录成功",maps);
            }
        }
        return Result.fail("登录失败");
    }


    /**
     * 学生退出
     * @param
     * @return
     */
    @PostMapping("logout")
    public Result logout(HttpServletRequest request) {
        JWTUtils.deleteToken(request);
        return Result.succ("退出成功",null);
    }


    /**
     * jwt的测试类，被拦截的方法需要得到token的内容的测试类
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
        return Result.succ("获取token成功",map);
    }

    /**
     * 查询各个题型的数量
     */
    @PostMapping("selectallqsbytype")
    public Result selectallqsbytype(@RequestParam Integer projectId) {
        Map<String, Integer> stringIntegerMap = iQsTotalService.selectQsCount(projectId);
        return Result.succ("查询成功",stringIntegerMap);
    }



    /**
     * 添加试卷
     */
    @PostMapping("addpaper")
    public Result addpaper(@RequestBody TsPaper tsPaper, HttpServletRequest request) {
        String token = request.getHeader("token");
        String id = JWTUtils.verify(token).getClaim("id").asString();
        Integer paperId = iTsPaperService.addpaper(Integer.parseInt(id),tsPaper);
        //获取到tdDis的数据
        List<TsDis> listDis = tsPaper.getListDis();
        for(TsDis tsDis:listDis){
            tsDisService.addTsDis(paperId,tsDis);
        }
       //根据课程id查询所有各种题型的qsId

        iTsDetailsService.addTsDetails(paperId,tsPaper.getPrId());

        //添加
        return Result.succ("添加成功",paperId);
    }

    /**
     * 删除试卷
     */
    @PostMapping("deletepaper")
    public Result deletepaper(@RequestBody Integer[] paperIds) {
        Integer deletepaper = iTsPaperService.deletepaper(paperIds);
        return deletepaper>0?Result.succ("删除成功",deletepaper):Result.fail("删除失败");
    }

    /**
     * 查询所有试卷信息(根据课程和老师id)
     */
    @PostMapping("selectallpaper")
    public Result selectallpaper(@RequestParam Integer projectId,@RequestParam String tsName) {
        List<TsPaper> tsPapers = iTsPaperService.selectByPr(projectId, tsName);
        return Result.succ("查询成功",tsPapers);
    }

    /**
     * 回收站查找试卷
     */
    @GetMapping("selectbyrecycle/{projectId}")
    public Result selectbyrecycle(@PathVariable("projectId") Integer projectId) {
        List<TsPaper> selectbyrecycle = iTsPaperService.selectbyrecycle(projectId);
        return Result.succ("查询成功",selectbyrecycle);
    }

    /**
     * 回收站恢复试卷
     */
    @PostMapping("bankbyrecycle")
    public Result bankbyrecycle(@RequestParam Integer paperId) {
        iTsPaperService.bankbyrecycle(paperId);
        return Result.succ("恢复成功",null);
    }

    /**
     * 封存试卷
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
        return sealpaper>0?Result.succ("封存成功",sealpaper):Result.fail("封存失败");
    }

    /**
     * 解封试卷
     */
    @PostMapping("nosealpaper")
    public Result nosealpaper(@RequestParam Integer paperId,@RequestParam String password) {
         iTsPaperService.nosealpaper(paperId,password);
         return Result.succ("解封成功",null);
    }






    /**
     * 解封忘记密码发送邮箱
     */
    @PostMapping("forgetpass")
    public Result forgetpass(HttpServletRequest request) {
        try {
            String token = request.getHeader("token");
            String id = JWTUtils.verify(token).getClaim("id").asString();
            //通过id查询邮箱
            String mail = sysUserService.selectMail(Integer.parseInt(id));
            mailService.sendHtmlMail(mail, "重置密码请点击该链接：", "<h1>http://localhost:10010/teacher/repassword</h1>");
            return Result.succ("发送成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("发送失败");
        }
    }

    /**
     * 重置密码
     */
    @GetMapping("repassword")
    public Result forgetpass(Integer paperId,String newPassword) {
        Integer forgetpass = iTsPaperService.forgetpass(paperId, newPassword);
        return forgetpass>0?Result.succ("重置成功",forgetpass):Result.fail("重置失败");
    }

    /**
     *查看试卷详情
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
        //根据qsId查询
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
        return Result.succ("查询成功",map);
    }

    /**
     * 查看属于该课程的所用学生
     */
    @GetMapping("selectstubypro")
    public Result selectstubypro( Integer proId) {
        List<Student> students = iStudentService.selectBypr(proId);
        return Result.succ("查询成功",students);
    }

        /**
         * 发布试卷
         */
    @PostMapping("publicpaper/{paperId}")
    public Result publicpaper(@PathVariable("paperId") Integer paperId,@RequestBody TsPublic tsPublic) {
        int i = iTsPublicService.addTsPublic(tsPublic,paperId);
        List<Integer> studentList = tsPublic.getStudentList();
        for(Integer stId:studentList){
            String s = sysUserService.selectName(stId);
            iTsAnsService.addTsAns(i,stId,s);
        }
        return i>0?Result.succ("发布成功",i):Result.fail("发布失败");
    }

    /**
     * 展示需要批阅的答案
     */
    @GetMapping("showreadpaper")
    public Result showreadpaper(Integer tsAnsId) {
        HashMap<String, Object> map = new HashMap<>();
        //根据pubId查询paperId
        TsAns tsAns = iTsAnsService.selectById(tsAnsId);
        Integer paperId = iTsPublicService.selectByPubId(tsAns.getPubId());
        map.put("tsAns",tsAns); //姓名和学生id
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
        //根据qsId查询
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
        return Result.succ("查询成功",map);
    }

    /**
     * 批阅答案
     */
    @PostMapping("readpaper/{tsAnsId}")
    public Result readpaper(@PathVariable("tsAnsId") Integer tsAnsId,@RequestBody TsAns tsAns){
        int i = iTsAnsService.updateTsAns(tsAnsId, tsAns);
        return Result.succ("批阅成功",i);
    }

}
