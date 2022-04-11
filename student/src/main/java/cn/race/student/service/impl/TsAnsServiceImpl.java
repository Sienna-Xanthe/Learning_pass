package cn.race.student.service.impl;

import cn.race.student.mapper.TsAnsMapper;
import cn.race.student.pojo.StdAns;
import cn.race.student.pojo.TsAns;
import cn.race.student.service.ITsAnsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public int updateStdAns(Integer pubId, Integer stuId) {
        LambdaQueryWrapper<TsAns> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsAns::getPubId,pubId)
                .eq(TsAns::getStId,stuId);
        TsAns tsAns = new TsAns();
        tsAns.setStStatusId(3);//已完成
        tsAns.setStSubmitTime(new Date());
        int update = tsAnsMapper.update(tsAns, lambdaQueryWrapper);
        return update;
    }

    @Override
    public TsAns selectByStuIdAndPubId(Integer pubId, Integer stuId) {
        LambdaQueryWrapper<TsAns> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsAns::getPubId,pubId)
                .eq(TsAns::getStId,stuId);
        TsAns tsAns = tsAnsMapper.selectOne(lambdaQueryWrapper);
        return tsAns;
    }
}
