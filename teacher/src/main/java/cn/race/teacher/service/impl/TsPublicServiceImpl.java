package cn.race.teacher.service.impl;

import cn.race.teacher.output.OutExams;
import cn.race.teacher.pojo.TsPublic;
import cn.race.teacher.mapper.TsPublicMapper;
import cn.race.teacher.service.ITsPublicService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public int addTsPublic(TsPublic tsPublic,Integer paperId) {

        tsPublic.setStatusId(1); //未开始
        tsPublic.setTotalNum(tsPublic.getStudentList().size()); //总人数
        tsPublic.setTsId(paperId);
        tsPublicMapper.insert(tsPublic);
        Integer id = tsPublic.getId();
        return id;
    }

    @Override
    public Integer selectByPubId(Integer pubId) {
        LambdaQueryWrapper<TsPublic> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsPublic::getId,pubId);
        TsPublic tsPublic = tsPublicMapper.selectOne(lambdaQueryWrapper);
        return tsPublic.getTsId();
      }
    @Override
    public Page<OutExams> selectexams(Page<OutExams> outExamsPage, Integer pr_id) {
        QueryWrapper<OutExams> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pr_id", pr_id)
                .eq("deleted", 0);
        Page<OutExams> selectexams = baseMapper.selectexams(outExamsPage, queryWrapper);
        return selectexams;
    }
}
