package cn.race.teacher.pojo;

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
 * @author pxy
 * @since 2022-04-08
 */
@Getter
@Setter
@TableName("ans_details")
public class AnsDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 答题id
     */
    @TableField("ans_id")
    private Integer ansId;

    /**
     * 答案
     */
    @TableField("ans")
    private String ans;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
