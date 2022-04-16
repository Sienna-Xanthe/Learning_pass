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
@TableName("std_ans")
public class StdAns implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    @TableField("st_id")
    private Integer stId;

    /**
     * 发行id
     */
    @TableField("pub_id")
    private Integer pubId;

    /**
     * 题目id
     */
    @TableField("qs_id")
    private Integer qsId;

//    /**
//     * 学生答案
//     */
//    @TableField("st_ans")
//    private String stAns;

    /**
     * 学生得分
     */
    @TableField("st_sc")
    private String stSc;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

//    @TableField(exist = false)
//    private List<AnsDetails> list = new ArrayList<>();

    @TableField(exist = false)
    private List<String> list = new ArrayList<>();


}
