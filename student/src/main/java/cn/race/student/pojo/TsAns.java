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
@TableName("ts_ans")
public class TsAns implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 试卷发行id
     */
    @TableField("pub_id")
    private Integer pubId;

    /**
     * 学生姓名
     */
    @TableField("st_name")
    private String stName;

    /**
     * 学生学号
     */
    @TableField("st_num")
    private String stNum;

    /**
     * 学生状态
     */
    @TableField("st_status_id")
    private Integer stStatusId;

    /**
     * 学生开始作答时间
     */
    @TableField("st_start_time")
    private Date stStartTime;

    /**
     * 学生提交时间
     */
    @TableField("st_submit_time")
    private Date stSubmitTime;

    /**
     * 学生ip
     */
    @TableField("st_ip")
    private String stIp;

    /**
     * 正确率
     */
    @TableField("correct_rate")
    private String correctRate;

    /**
     * 学生得分
     */
    @TableField("st_score")
    private String stScore;

    /**
     * 批阅状态
     */
    @TableField("approve_id")
    private Integer approveId;

    @TableField("st_id")
    private Integer stId;

    /**
     * 批阅ip
     */
    @TableField("app_ip")
    private String appIp;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
