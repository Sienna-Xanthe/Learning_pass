package cn.race.teacher.service.impl;

import cn.race.teacher.pojo.TsAns;
import cn.race.teacher.mapper.TsAnsMapper;
import cn.race.teacher.service.ITsAnsService;
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
public class TsAnsServiceImpl extends ServiceImpl<TsAnsMapper, TsAns> implements ITsAnsService {

    @Autowired
    TsAnsMapper tsAnsMapper;

    @Override
    public int addTsAns(Integer pubId, Integer stId ,String name) {
        TsAns tsAns = new TsAns();
        tsAns.setPubId(pubId);
        tsAns.setStId(stId);
        tsAns.setStStatusId(1); //待作答
        tsAns.setStName(name);
        tsAns.setApproveId(1); //未批阅
        int insert = tsAnsMapper.insert(tsAns);
        return insert;
    }

    @Override
    public TsAns selectById(Integer tsAnsId) {
        LambdaQueryWrapper<TsAns> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsAns::getId,tsAnsId);
        TsAns tsAns = tsAnsMapper.selectOne(lambdaQueryWrapper);
        return tsAns;
    }

    @Override
    public int updateTsAns(Integer tsAnsId, TsAns tsAns) {
        LambdaQueryWrapper<TsAns> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsAns::getId,tsAnsId);
        int update = tsAnsMapper.update(tsAns, lambdaQueryWrapper);
        return update;
    }
}
