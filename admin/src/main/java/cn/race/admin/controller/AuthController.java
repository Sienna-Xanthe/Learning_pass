package cn.race.admin.controller;

import cn.hutool.core.map.MapUtil;
import cn.race.admin.entity.SysUser;
import cn.race.common.response.Result;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthController extends BaseController{

	@Autowired
	Producer producer;

//	/**
//	 * 获取验证码
//	 * @return
//	 * @throws IOException
//	 */
//	@GetMapping("/captcha")
//	public Result captcha() throws IOException {
//
//		String key = UUID.randomUUID().toString();
//		String code = producer.createText();
//
//		// 为了测试
////		key = "aaaaa";
////		code = "11111";
//		System.out.println(key);
//		System.out.println(code);
//
//		BufferedImage image = producer.createImage(code);
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		ImageIO.write(image, "jpg", outputStream);
//
//		BASE64Encoder encoder = new BASE64Encoder();
//		String str = "data:image/jpeg;base64,";
//
//		String base64Img = str + encoder.encode(outputStream.toByteArray());
//
//		redisUtil.hset(Const.CAPTCHA_KEY, key, code, 120);
//
//
//		return Result.succ(
//				MapUtil.builder()
//						.put("token", key)
//						.put("captchaImg", base64Img)
//						.build()
//
//		);
//	}

	/**
	 * 获取用户信息接口
	 * @param principal
	 * @return
	 */
	@GetMapping("/sys/userInfo")
	public Result userInfo(Principal principal) {

		SysUser sysUser = sysUserService.getByUsername(principal.getName());

		return Result.succ(MapUtil.builder()
				.put("id", sysUser.getId())
				.put("username", sysUser.getUsername())
				.put("avatar", sysUser.getAvatar())
				.put("created", sysUser.getCreated())
				.map()
		);
	}

	public static void main(String[] args) {
		String pass = "123456";
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		boolean f = bcryptPasswordEncoder.matches("123456","$2a$10$1cGDZVPPgIvyH2VKpdPUquBse33G2WOLYWY/8zqDLJITwGERxdMeW");
		System.out.println(f);
	}
	}



