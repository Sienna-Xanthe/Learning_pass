package cn.race.student.service.impl;

import cn.race.student.convert.TsDisConvert;
import cn.race.student.dto.TsDisDto;
import cn.race.student.mapper.TsDisMapper;
import cn.race.student.pojo.TsDis;
import cn.race.student.service.ITsDisService;
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
public class TsDisServiceImpl extends ServiceImpl<TsDisMapper, TsDis> implements ITsDisService {
    @Autowired
    TsDisMapper tsDisMapper;

    @Override
    public List<TsDisDto> selectpaperdetail(Integer paperId) {
        LambdaQueryWrapper<TsDis> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsDis::getTsId,paperId);
        List<TsDis> tsDis = tsDisMapper.selectList(lambdaQueryWrapper);
        return TsDisConvert.INSTANCE.entity2Dto(tsDis);
    }
}
