package cn.race.student.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutExam {
    /**
     * 试卷id
     */
    private Integer id;

    /**
     * 试卷名称
     */
    private String tsName;

    /**
     * 试卷总分
     */
    private Double tsScore;

    /**
     * 试卷难易度
     */
    private Integer levelId;

    /**
     * 创建人id
     */
    private Integer tcId;

    /**
     * 课程id
     */
    private Integer prId;


    /**
     * 0：未删除 1：删除
     */
    private Integer deleted;
//    ------------------------------------------

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 时间限制
     */
    private String limitTime;

    /**
     * 试卷状态id
     */
    private Integer statusId;

//    -------------------------------
    /**
     * 学生状态
     */
    private Integer stStatusId;
}
