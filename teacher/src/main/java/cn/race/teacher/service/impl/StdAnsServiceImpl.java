package cn.race.teacher.service.impl;

import cn.race.teacher.mapper.TsPublicMapper;
import cn.race.teacher.pojo.StdAns;
import cn.race.teacher.mapper.StdAnsMapper;
import cn.race.teacher.pojo.TsPublic;
import cn.race.teacher.service.IStdAnsService;
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
public class StdAnsServiceImpl extends ServiceImpl<StdAnsMapper, StdAns> implements IStdAnsService {
    @Autowired
    StdAnsMapper stdAnsMapper;

    @Override
    public List<StdAns> selectDe(Integer pubId, Integer stuId) {
        LambdaQueryWrapper<StdAns> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(StdAns::getPubId,pubId)
                .eq(StdAns::getStId,stuId);
        List<StdAns> stdAns = stdAnsMapper.selectList(lambdaQueryWrapper);
        return stdAns;
    }
}
