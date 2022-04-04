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
@TableName("ts_dis")
public class TsDis implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 试卷id
     */
    @TableField("ts_id")
    private Integer tsId;

    /**
     * 题型id
     */
    @TableField("type_id")
    private Integer typeId;

    /**
     * 题目分数
     */
    @TableField("qs_sc")
    private Double qsSc;

    /**
     * 题目描述
     */
    @TableField("qs_desc")
    private String qsDesc;

    /**
     * 题目数量
     */
    @TableField("qs_count")
    private Integer qsCount;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
