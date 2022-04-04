package cn.race.teacher.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2022-03-29
 */
@Getter
@Setter
@TableName("project")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程名称
     */
    @TableField("pr_name")
    private String prName;

    /**
     * 课程说明
     */
    @TableField("pr_desc")
    private String prDesc;

    /**
     * 课程封面
     */
    @TableField("pr_pic")
    private String prPic;

    /**
     * 课程老师
     */
    @TableField("pr_teacher")
    private String prTeacher;

    /**
     * 0：未删除	1：删除
     */
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 教师id
     */
    @TableField("tc_id")
    private Integer tcId;


}
