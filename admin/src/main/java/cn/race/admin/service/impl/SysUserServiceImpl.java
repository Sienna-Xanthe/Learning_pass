package cn.race.admin.service.impl;


import cn.race.admin.entity.SysMenu;
import cn.race.admin.entity.SysRole;
import cn.race.admin.entity.SysUser;
import cn.race.admin.mapper.SysUserMapper;
import cn.race.admin.service.SysMenuService;
import cn.race.admin.service.SysRoleService;
import cn.race.admin.service.SysUserService;
import cn.race.admin.utils.RedisUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
根据用户id获取，用户角色和用户可以操作的菜单
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Autowired
	SysRoleService sysRoleService;

	@Autowired
	SysUserMapper sysUserMapper;

	@Autowired
	SysMenuService sysMenuService;

	@Autowired
	RedisUtil redisUtil;

	@Override
	public SysUser getByUsername(String username) {

		return getOne(new QueryWrapper<SysUser>().eq("username", username));
	}

	@Override
	public String getUserAuthorityInfo(Long userId) {

		SysUser sysUser = sysUserMapper.selectById(userId);

		//  ROLE_admin,ROLE_normal,sys:user:list,....
		String authority = "";

		if (redisUtil.hasKey("GrantedAuthority:" + sysUser.getUsername())) {
			authority = (String) redisUtil.get("GrantedAuthority:" + sysUser.getUsername());

		} else {
			// 获取角色编码
			List<SysRole> roles = sysRoleService.list(new QueryWrapper<SysRole>()
					.inSql("id", "select role_id from sys_user_role where user_id = " + userId));

			if (roles.size() > 0) {
				String roleCodes = roles.stream().map(r -> "ROLE_" + r.getCode()).collect(Collectors.joining(","));
				authority = roleCodes.concat(",");
			}

			// 获取菜单操作编码
			List<Long> menuIds = sysUserMapper.getNavMenuIds(userId);
			if (menuIds.size() > 0) {

				List<SysMenu> menus = sysMenuService.listByIds(menuIds);
				String menuPerms = menus.stream().map(m -> m.getPerms()).collect(Collectors.joining(","));

				authority = authority.concat(menuPerms);
			}

			//将权限信息放入redis中，方便读取
			redisUtil.set("GrantedAuthority:" + sysUser.getUsername(), authority, 60 * 60);
		}

		return authority;
	}

	@Override
	public void clearUserAuthorityInfo(String username) {
		redisUtil.del("GrantedAuthority:" + username);
	}

	@Override
	public void clearUserAuthorityInfoByRoleId(Long roleId) {

		List<SysUser> sysUsers = this.list(new QueryWrapper<SysUser>()
				.inSql("id", "select user_id from sys_user_role where role_id = " + roleId));

		sysUsers.forEach(u -> {
			this.clearUserAuthorityInfo(u.getUsername());
		});

	}

	/**
	 * 角色发生了改变后，对redis中的缓存进行清理
	 * @param menuId
	 */
	@Override
	public void clearUserAuthorityInfoByMenuId(Long menuId) {
		List<SysUser> sysUsers = sysUserMapper.listByMenuId(menuId);

		sysUsers.forEach(u -> {
			this.clearUserAuthorityInfo(u.getUsername());
		});
	}
}
