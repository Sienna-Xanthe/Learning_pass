package cn.race.teacher.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
@Getter
@Setter
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("avatar")
    private String avatar;

    @TableField("email")
    private String email;

    @TableField("city")
    private String city;

    @TableField("created")
    private Date created;

    @TableField("updated")
    private Date updated;

    @TableField("last_login")
    private Date lastLogin;

    @TableField("statu")
    private Integer statu;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    @TableField("sex")
    private String sex;

    @TableField(exist = false)
    private List<SysRole> sysRoles = new ArrayList<>();


}
