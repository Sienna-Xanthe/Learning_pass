package cn.race.teacher.output;

import cn.race.teacher.pojo.QsOp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutTotal {
    /**
     * 题目id
     */
    private Integer id;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 类型名称
     */
    private String type;
    /**
     * 难易度
     */
    private String level;

    /**
     * 难易度id
     */
    private Integer levelId;



    /**
     * 课程id
     */
    private Integer prId;

    /**
     * 老师id
     */
    private Integer tcId;

    /**
     * 老师姓名
     */
    private String username;


    /**
     * 题目描述
     */
    private String question;

    /**
     * 选项
     */
    private List<QsOp> opList;

}
