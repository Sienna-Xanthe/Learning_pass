package cn.race.excle.pojo;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * sys_user
 * @author
 */
@Data
@TableName("team")
public class Team implements Serializable {

    @ExcelIgnore
    @TableId(type = IdType.AUTO )
    private Integer id;


    @TableField("work_id")
    private Integer workId;

    @ExcelProperty("队员姓名")
    @TableField("team_name")
    private String teamName;

    @ExcelProperty("性别")
    @TableField("team_sex")

    private String teamSex;

    @ExcelProperty("生日")
    @TableField("team_birthday")

    private String teamBirthday;

    @ExcelProperty("身份证")
    @TableField("team_card")

    private String teamCard;

    @ExcelProperty("专业")
    @TableField("team_major")

    private String teamMajor;

    @ExcelProperty("学号")
    @TableField("team_num")

    private String teamNum;

    @ExcelProperty("手机号")
    @TableField("team_phone")

    private String teamPhone;

}
