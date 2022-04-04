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
