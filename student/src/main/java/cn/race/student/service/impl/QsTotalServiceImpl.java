package cn.race.student.service.impl;


import cn.race.student.convert.QsTotalConvert;
import cn.race.student.dto.QsTotalDto;
import cn.race.student.mapper.QsTotalMapper;
import cn.race.student.pojo.QsTotal;
import cn.race.student.service.IQsTotalService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
@Service
public class QsTotalServiceImpl extends ServiceImpl<QsTotalMapper, QsTotal> implements IQsTotalService {
    @Autowired
    QsTotalMapper qsTotalMapper;
    @Override
    public QsTotalDto selectpaperdetail(Integer qsId) {
        LambdaQueryWrapper<QsTotal> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(QsTotal::getId,qsId);
        QsTotal qsTotal = qsTotalMapper.selectOne(lambdaQueryWrapper);
        return QsTotalConvert.INSTANCE.entity2Dto(qsTotal);
    }
}
