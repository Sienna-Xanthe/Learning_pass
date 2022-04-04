package cn.race.student.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SysUserDto {

    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "角色不能为空")
    private String role;

}
