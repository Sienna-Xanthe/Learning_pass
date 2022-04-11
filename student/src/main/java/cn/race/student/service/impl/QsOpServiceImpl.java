package cn.race.student.service.impl;


import cn.race.student.mapper.QsOpMapper;
import cn.race.student.pojo.QsOp;
import cn.race.student.service.IQsOpService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class QsOpServiceImpl extends ServiceImpl<QsOpMapper, QsOp> implements IQsOpService {
    @Autowired
    QsOpMapper qsOpMapper;

    @Override
    public List<QsOp> selectpaperdetail(Integer qsId) {
        LambdaQueryWrapper<QsOp> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(QsOp::getQsId,qsId);
        List<QsOp> qsOps = qsOpMapper.selectList(lambdaQueryWrapper);
        return qsOps;
    }
}
