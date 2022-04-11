package cn.race.teacher.service;

import cn.race.teacher.input.InputSlctQs;
import cn.race.teacher.output.OutQsTotal;
import cn.race.teacher.output.OutTotal;
import cn.race.teacher.pojo.QsTotal;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
public interface IQsTotalService extends IService<QsTotal> {

    int addQsTotal(QsTotal qsTotal);

    int deletequestion(Integer id);

    Page<OutQsTotal> selectByTcId(InputSlctQs inputSlctQs);

    Page<OutQsTotal> test(Page<OutQsTotal> iPage);

    OutTotal selectTotal(Integer id);
}
