package cn.race.student.pojo;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("qs_total")
public class QsTotal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型id
     */
    @TableField("type_id")
    private Integer typeId;

    /**
     * 难易度id
     */
    @TableField("level_id")
    private Integer levelId;

    /**
     * 课程id
     */
    @TableField("pr_id")
    private Integer prId;

    /**
     * 老师id
     */
    @TableField("tc_id")
    private Integer tcId;

    /**
     * 是否为主观题
     */
    @TableField("is_sub")
    private Integer isSub;

    /**
     * 题目描述
     */
    @TableField("question")
    private String question;

    /**
     * 0：未删除 1：删除
     */
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
