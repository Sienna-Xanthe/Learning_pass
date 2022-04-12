package cn.race.teacher.service.impl;

import cn.race.teacher.pojo.QsOp;
import cn.race.teacher.mapper.QsOpMapper;
import cn.race.teacher.service.IQsOpService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        lambdaQueryWrapper.eq(QsOp::getQsId, qsId);
        List<QsOp> qsOps = qsOpMapper.selectList(lambdaQueryWrapper);
        return qsOps;
    }
    @Override
    public boolean addQsOp(List<QsOp> qsOps) {
        boolean b = this.saveBatch(qsOps);
        return b;
    }

    @Override
    public List<QsOp> selectByQsId(Integer id) {
        QueryWrapper<QsOp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("qs_id",id);
        List<QsOp> qsOpList = this.baseMapper.selectList(queryWrapper);
        return qsOpList;
    }
}
