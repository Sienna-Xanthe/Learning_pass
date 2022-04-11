package cn.race.teacher.service.impl;

import cn.race.teacher.pojo.QsLevel;
import cn.race.teacher.mapper.QsLevelMapper;
import cn.race.teacher.service.IQsLevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
@Service
public class QsLevelServiceImpl extends ServiceImpl<QsLevelMapper, QsLevel> implements IQsLevelService {

    @Override
    public List<QsLevel> selectlevels() {
        List<QsLevel> list = this.list();
        return list;
    }
}
