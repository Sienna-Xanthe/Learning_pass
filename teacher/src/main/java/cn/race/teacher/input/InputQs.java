package cn.race.teacher.input;

import cn.race.teacher.pojo.QsOp;
import cn.race.teacher.pojo.QsTotal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputQs {
    private QsTotal qsTotal;

    private List<QsOp> qsOp;
}
