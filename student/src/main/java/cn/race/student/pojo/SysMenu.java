package cn.race.student.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

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
@TableName("sys_menu")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父菜单ID，一级菜单为0
     */
    @TableField("parent_id")
    private Long parentId;

    @TableField("name")
    private String name;

    /**
     * 菜单URL
     */
    @TableField("path")
    private String path;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    @TableField("perms")
    private String perms;

    @TableField("component")
    private String component;

    /**
     * 类型     0：目录   1：菜单   2：按钮
     */
    @TableField("type")
    private Integer type;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 排序
     */
    @TableField("orderNum")
    private Integer orderNum;

    @TableField("created")
    private Date created;

    @TableField("updated")
    private Date updated;

    @TableField("statu")
    private Integer statu;


}
