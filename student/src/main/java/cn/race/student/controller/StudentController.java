package cn.race.student.controller;

import cn.race.common.response.BusinessException;
import cn.race.common.response.CommonErrorCode;
import cn.race.common.response.Result;
import cn.race.feign.clients.OssClient;
import cn.race.student.pojo.SysRole;
import cn.race.student.pojo.SysUser;
import cn.race.student.service.SysRoleService;
import cn.race.student.service.SysUserService;
import cn.race.student.util.JWTUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
@RestController
@RequestMapping("/student/")
public class StudentController {
    @Autowired
    private SysUserService sysUserService;


    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    OssClient ossClient;

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
            if(sysRole.getCode().equals(role)){
                Map<String, String> map1 = new HashMap<>();
                map1.put("id", sysUser.getId().toString());
                map1.put("username", sysUser.getUsername());
                //生成token令牌
                String token = JWTUtils.getToken(map1);

                return Result.succ("登录成功",token);
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

}
