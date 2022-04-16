package cn.race.student.controller;

import cn.race.common.response.BusinessException;
import cn.race.common.response.CommonErrorCode;
import cn.race.common.response.Result;
import cn.race.feign.clients.ExcelClient;
import cn.race.feign.clients.OssClient;
import cn.race.feign.clients.VideoClient;
import cn.race.student.dto.QsTotalDto;
import cn.race.student.dto.TsDisDto;
import cn.race.student.dto.TsPaperDto;
import cn.race.student.pojo.*;
import cn.race.student.service.*;
import cn.race.student.pojo.Project;
import cn.race.student.pojo.Student;
import cn.race.student.pojo.SysRole;
import cn.race.student.pojo.SysUser;
import cn.race.student.service.IStudentService;
import cn.race.student.service.SysRoleService;
import cn.race.student.service.SysUserService;

import cn.race.student.util.JWTUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/student/")
public class StudentController {
    @Autowired
    private SysUserService sysUserService;


    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    OssClient ossClient;

    @Autowired
    ITsPaperService iTsPaperService;

    @Autowired
    ITsPublicService iTsPublicService;

    @Autowired
    ITsAnsService iTsAnsService;

    @Autowired
    ITsDisService tsDisService;

    @Autowired
    ITsDetailsService iTsDetailsService;

    @Autowired
    IQsTotalService iQsTotalService;


    @Autowired
    IQsOpService iQsOpService;

    @Autowired
    IStudentService iStudentService;
    @Autowired
    IStdAnsService iStdAnsService;

    @Autowired
    AnsDetailsService ansDetailsService;
  
    @Autowired
    private IStudentService studentService;

    @Autowired
    VideoClient videoClient;
    
    @Autowired
    ExcelClient excelClient;

    /**
     * 使用feign-api远程调用oss服务
     * @param file
     * @return
     */
    @PostMapping("getOss")
    public Result getOss(@RequestParam("file") MultipartFile file){
        Result upload = ossClient.upload(file);
        return upload;
    }

    /**
     * 使用feign-api远程调用视频点播服务
     * @param file
     * @return
     */
    @PostMapping("getVideo")
    public Result getVideo(@RequestParam("file") MultipartFile file){
        Result result = videoClient.uploadAlyVideo(file);
        return result;
    }

    /**
     * 使用excel远程调用视频点播服务
     * @param file
     * @return
     */
    @PostMapping("getExcel")
    public Result getExcel(@RequestParam("file") MultipartFile file){
        Result result = excelClient.simpleRead(file);
        return result;
    }
    
    

    /**
     * 学生登录
     * @param
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestParam String username, @RequestParam String password,@RequestParam String role) {

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
            if(sysRole.getCode().equals(role) && ("student".equals(role) || "admin".equals(role))){
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
     * 显示答题页面
     */
    @GetMapping("showpaper/{pubId}")
    public Result test(HttpServletRequest request,@PathVariable("pubId") Integer pubId) {
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        String username = verify.getClaim("username").asString();
        String id = verify.getClaim("id").asString();

        HashMap<String, Object> map = new HashMap<>();
        map.put("student",username);
        //根据pubId查询paperId
        Integer paperId = iTsPublicService.selectByPubId(pubId);

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
     * 提交试卷
     */
    @PostMapping("commitpaper/{pubId}")
    public Result commitpaper(HttpServletRequest request,@PathVariable("pubId") Integer pubId,@RequestBody List<StdAns> stdAnslist) {
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        String id = verify.getClaim("id").asString();
        for(StdAns stdAns:stdAnslist){
            int i = iStdAnsService.addStdAns(pubId, Integer.parseInt(id), stdAns);
            List<String> list = stdAns.getList();
            for(String ansDetails:list){
            int i1 = ansDetailsService.addAnsDetails(i, ansDetails);
           }
        }
        int i = iTsAnsService.updateStdAns(pubId, Integer.parseInt(id));
        return Result.succ("添加成功",i);
    }

    /**
     * 显示已经答题页面详情
     */
    @GetMapping("showpapercomplet/{pubId}")
    public Result showpapercomplet(HttpServletRequest request,@PathVariable("pubId") Integer pubId){
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        String username = verify.getClaim("username").asString();
        String id = verify.getClaim("id").asString();

        HashMap<String, Object> map = new HashMap<>();
        map.put("student",username);
        //根据pubId查询paperId
        Integer paperId = iTsPublicService.selectByPubId(pubId);

        TsAns tsAns = iTsAnsService.selectByStuIdAndPubId(pubId, Integer.parseInt(id));
        map.put("tsAns",tsAns);

        List<StdAns> stdAns = iStdAnsService.selectDe(pubId, Integer.parseInt(id));
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
     * 学生查询课程
     * @param st_id
     * @param pr_name
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/selectprojects")
    public Result selectProjects(@RequestParam Integer st_id, @RequestParam String pr_name ,@RequestParam Integer page,@RequestParam Integer size){
        Page<Project> projectPage = new Page<>(page,size);
        projectPage = studentService.selectProjects(projectPage,st_id,pr_name);
//        return R.ok().message("查询成功").data("projects",projectPage);
        return Result.succ("查询成功",projectPage);
    }


    @PostMapping("/addproject")
    public Result addProject(@RequestBody Student student){
        boolean save = studentService.save(student);
        if(save) {
//            return R.ok().message("插入成功");
            return Result.succ("插入成功",null);
        }
//        return R.error().message("插入失败");
        return Result.fail("插入失败");
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("hahhaha");
        list.add("hahsdaasffhaha");
        list.add("hah安徽萨斯haha");
        list.add("sddfdfdf");
        System.out.println(list);

    }

}
