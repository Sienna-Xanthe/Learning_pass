package cn.race.teacher.controller;

import cn.race.common.response.BusinessException;
import cn.race.common.response.CommonErrorCode;
import cn.race.common.response.Result;
import cn.race.teacher.pojo.SysRole;
import cn.race.teacher.pojo.SysUser;
import cn.race.teacher.service.SysRoleService;
import cn.race.teacher.service.SysUserService;
import cn.race.teacher.util.JWTUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher/")
public class TeacherController {
    @Autowired
    private SysUserService sysUserService;


    @Autowired
    SysRoleService sysRoleService;
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
