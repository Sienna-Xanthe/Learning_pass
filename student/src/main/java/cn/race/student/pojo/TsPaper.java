package cn.race.student.pojo;

import com.baomidou.mybatisplus.annotation.*;
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
@TableName("ts_paper")
public class TsPaper implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    /**
     * 试卷id
     */
    @TableField("ts_name")
    private String tsName;

    /**
     * 试卷总分
     */
    @TableField("ts_score")
    private Double tsScore;

    /**
     * 试卷难易度
     */
    @TableField("level_id")
    private Integer levelId;

    /**
     * 创建人id
     */
    @TableField("tc_id")
    private Integer tcId;

    /**
     * 课程id
     */
    @TableField("pr_id")
    private Integer prId;

    /**
     * 0：未删除 1：删除
     */
    @TableField("deleted")
    @TableLogic
    private String deleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
