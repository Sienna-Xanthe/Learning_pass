package cn.race.teacher.service;


import cn.race.teacher.pojo.SysUser;

public interface SysUserService {

    SysUser login(String username, String password);
}
