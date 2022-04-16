package cn.race.teacher.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutGrage {
    private String stName;
    private Double stScore;
    private String tsName;
    private Integer id;
}
