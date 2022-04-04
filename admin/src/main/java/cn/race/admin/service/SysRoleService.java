package cn.race.admin.service;


import cn.race.admin.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface SysRoleService extends IService<SysRole> {

	List<SysRole> listRolesByUserId(Long userId);

}
