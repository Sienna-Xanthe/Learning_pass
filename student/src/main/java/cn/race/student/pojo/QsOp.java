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
@TableName("qs_op")
public class QsOp implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 题目id
     */
    @TableField("qs_id")
    private Integer qsId;

    /**
     * 题目选项/答案
     */
    @TableField("qs_option")
    private String qsOption;

    /**
     * 1：正确  0：错误
     */
    @TableField("is_true")
    private String isTrue;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
