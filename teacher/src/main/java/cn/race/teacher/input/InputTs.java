package cn.race.teacher.input;

import cn.race.teacher.pojo.TsDetails;
import cn.race.teacher.pojo.TsDis;
import cn.race.teacher.pojo.TsPaper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputTs {
    /**
     * 试卷基础
     */
    private TsPaper tsPaper;

    /**
     * 试卷题目类型及分数
     */
    private List<TsDis> disList;

    /**
     * 试卷详情
     */
    private List<TsDetails> detailsList;
}
