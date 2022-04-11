package cn.race.student.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputStartTime {

    private Integer id;

    /**
     * 学生开始作答时间
     */
    private Date stStartTime;

    /**
     * 学生ip
     */
    private String stIp;
}
