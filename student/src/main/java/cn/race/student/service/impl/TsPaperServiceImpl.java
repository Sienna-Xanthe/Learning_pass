package cn.race.student.service.impl;

import cn.race.student.convert.TsPaperConvertor;
import cn.race.student.dto.TsPaperDto;
import cn.race.student.mapper.TsPaperMapper;
import cn.race.student.pojo.TsPaper;
import cn.race.student.service.ITsPaperService;
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
public class TsPaperServiceImpl extends ServiceImpl<TsPaperMapper, TsPaper> implements ITsPaperService {
    @Autowired
    TsPaperMapper tsPaperMapper;

    @Override
    public TsPaperDto selectById(Integer paperId) {
        LambdaQueryWrapper<TsPaper> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsPaper::getId,paperId);
        TsPaper tsPaper = tsPaperMapper.selectOne(lambdaQueryWrapper);
        return TsPaperConvertor.INSTANCE.entity2Dto(tsPaper);
    }

}
