package cn.race.student.service.impl;

import cn.race.student.mapper.TsPublicMapper;
import cn.race.student.pojo.TsPublic;
import cn.race.student.service.ITsPublicService;
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
public class TsPublicServiceImpl extends ServiceImpl<TsPublicMapper, TsPublic> implements ITsPublicService {
    @Autowired
    TsPublicMapper tsPublicMapper;

    @Override
    public Integer selectByPubId(Integer pubId) {
        LambdaQueryWrapper<TsPublic> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsPublic::getId,pubId);
        TsPublic tsPublic = tsPublicMapper.selectOne(lambdaQueryWrapper);
        return tsPublic.getTsId();
    }

    @Override
    public String selectSing(Integer pubId) {
        LambdaQueryWrapper<TsPublic> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsPublic::getId,pubId);
        TsPublic tsPublic = tsPublicMapper.selectOne(lambdaQueryWrapper);
        return tsPublic.getSing();
    }
}
