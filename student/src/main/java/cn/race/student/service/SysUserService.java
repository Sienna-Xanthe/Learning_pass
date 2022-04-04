package cn.race.student.service;


import cn.race.student.dto.SysUserDto;
import cn.race.student.pojo.SysUser;


public interface SysUserService {

    SysUser login(String username,String password);
}
