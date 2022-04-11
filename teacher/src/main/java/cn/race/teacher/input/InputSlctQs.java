package cn.race.teacher.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputSlctQs {
    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 难易度id
     */
    private Integer levelId;

    /**
     * 课程id
     */
    private Integer prId;

    /**
     * 题目描述
     */
    private String question;

    private Integer page;

    private Integer size;

}
