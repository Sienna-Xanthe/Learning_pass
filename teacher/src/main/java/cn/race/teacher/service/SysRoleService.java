package cn.race.teacher.service;






import cn.race.teacher.pojo.SysRole;

import java.util.List;


public interface SysRoleService{

    List<SysRole> listRolesByUserId(Long userId);

}
