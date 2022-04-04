package cn.race.student.service;




import cn.race.student.pojo.SysRole;

import java.util.List;


public interface SysRoleService{

    List<SysRole> listRolesByUserId(Long userId);

}
