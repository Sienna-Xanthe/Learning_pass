package cn.race.teacher.service;


import cn.race.teacher.pojo.SysUser;

public interface SysUserService {

    SysUser login(String username, String password);

    String selectMail(Integer id);

    String selectName(Integer id);
}
