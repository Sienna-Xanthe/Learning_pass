package cn.race.teacher.output;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutExams {

    private Integer id;
    /**
     * 试卷id
     */
    private Integer tsId;

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

    /**
     * 考试总人数
     */
    private Integer totalNum;

    /**
     * 试卷id
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
     * 创建人姓名
     */
    private String username;
}
