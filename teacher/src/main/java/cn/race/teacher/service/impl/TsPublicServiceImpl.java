package cn.race.teacher.service.impl;

import cn.race.teacher.output.OutExams;
import cn.race.teacher.pojo.TsPublic;
import cn.race.teacher.mapper.TsPublicMapper;
import cn.race.teacher.service.ITsPublicService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public Page<OutExams> selectexams(Page<OutExams> outExamsPage, Integer pr_id) {
        QueryWrapper<OutExams> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pr_id", pr_id)
                .eq("deleted", 0);
        Page<OutExams> selectexams = baseMapper.selectexams(outExamsPage, queryWrapper);
        return selectexams;
    }
}
