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
public class OuTsStudents {
    private Integer id;

    /**
     * 试卷发行id
     */
    private Integer pubId;

    /**
     * 学生姓名
     */
    private String stName;

    /**
     * 学生学号
     */
    private String stNum;

    /**
     * 学生状态
     */
    private Integer stStatusId;

    /**
     * 学生作答状态
     */
    private String status;

    /**
     * 学生开始作答时间
     */
    private Date stStartTime;

    /**
     * 学生提交时间
     */
    private Date stSubmitTime;

    /**
     * 学生ip
     */
    private String stIp;

    /**
     * 正确率
     */
    private String correctRate;

    /**
     * 学生得分
     */
    private String stScore;

    /**
     * 批阅状态
     */
    private Integer approveId;


    private String approve;

    /**
     * 批阅ip
     */
    private String appIp;

    /**
     * 学生id
     */
    private Integer stId;

//    ---------------------------------

    private String username;

    private String email;

    private String city;

    private String phone;

//    -------------------------------

}
