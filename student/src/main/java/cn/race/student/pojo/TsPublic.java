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
@TableName("ts_public")
public class TsPublic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 试卷id
     */
    @TableField("ts_id")
    private Integer tsId;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 时间限制
     */
    @TableField("limit_time")
    private String limitTime;

    /**
     * 试卷状态id
     */
    @TableField("status_id")
    private String statusId;

    /**
     * 考试总人数
     */
    @TableField("total_num")
    private Integer totalNum;

//    /**
//     * 封存密码
//     */
//    @TableField("ts_pw")
//    private String tsPw;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
