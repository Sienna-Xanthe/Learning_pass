package cn.race.teacher.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
