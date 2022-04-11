package cn.race.teacher.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
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
    private Integer statusId;

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

    @TableField("question_disorder")
    private Integer questionDisorder; //1为乱序

    @TableField("option_disorder")
    private Integer optionDisorder; //选项乱序

    @TableField("jump_count")
    private Integer jumpCount; //跳出次数

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    List<Integer> studentList = new ArrayList<>();


}
