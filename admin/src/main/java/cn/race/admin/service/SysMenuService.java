package cn.race.admin.service;


import cn.race.admin.common.dto.SysMenuDto;
import cn.race.admin.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface SysMenuService extends IService<SysMenu> {

	List<SysMenuDto> getCurrentUserNav();

	List<SysMenu> tree();

	SysMenu selectById(Long id);

}
