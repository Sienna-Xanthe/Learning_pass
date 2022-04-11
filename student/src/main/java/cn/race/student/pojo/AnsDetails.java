package cn.race.student.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@TableName("ans_details")
public class AnsDetails {
    private static final long serialVersionUID = 1L;

    @TableId("id")
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
