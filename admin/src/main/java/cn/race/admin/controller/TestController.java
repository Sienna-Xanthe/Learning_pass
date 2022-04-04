package cn.race.admin.controller;


import cn.race.admin.service.SysUserService;
import cn.race.common.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

	@Autowired
	SysUserService sysUserService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@PreAuthorize("hasRole('admin')")
	@GetMapping("/test")
	public Result test() {
		return Result.succ(sysUserService.list());
	}

	// 普通用户、超级管理员
	@PreAuthorize("hasAuthority('sys:user:list')")
	@GetMapping("/test/pass")
	public Result pass() {

		// 加密后密码
		String password = bCryptPasswordEncoder.encode("111111");

		boolean matches = bCryptPasswordEncoder.matches("111111", password);

		System.out.println("匹配结果：" + matches);

		return Result.succ(password);
	}

	@GetMapping("/get")
	public Result updatePass(Principal principal) {
		String name = principal.getName();
		return Result.succ("查询成功",name);
	}

}
