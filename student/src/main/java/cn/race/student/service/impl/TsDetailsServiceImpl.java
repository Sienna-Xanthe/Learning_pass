package cn.race.student.service.impl;

import cn.race.student.mapper.TsDetailsMapper;
import cn.race.student.pojo.TsDetails;
import cn.race.student.service.ITsDetailsService;
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
public class TsDetailsServiceImpl extends ServiceImpl<TsDetailsMapper, TsDetails> implements ITsDetailsService {
    @Autowired
    TsDetailsMapper tsDetailsMapper;
    @Override
    public List<TsDetails> selectpaperdetail(Integer paperId) {
        LambdaQueryWrapper<TsDetails> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsDetails::getTsId,paperId);
        List<TsDetails> tsDetails = tsDetailsMapper.selectList(lambdaQueryWrapper);
        return tsDetails;
    }
}
