package cn.race.student.service.impl;

import cn.race.student.mapper.TsAnsMapper;
import cn.race.student.output.OutExam;
import cn.race.student.pojo.TsAns;
import cn.race.student.service.ITsAnsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
public class TsAnsServiceImpl extends ServiceImpl<TsAnsMapper, TsAns> implements ITsAnsService {

    @Override
    public Page<OutExam> selectExams(Page<OutExam> outExamPage, Integer st_id, Integer pr_id, Integer st_status_id) {
        QueryWrapper<OutExam> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("st_id",st_id)
                .eq("pr_id",pr_id)
                .eq("st_status_id",st_status_id)
                .eq("deleted",0)
        ;
        Page<OutExam> selectexams = baseMapper.selectexams(outExamPage, queryWrapper);
        return selectexams;
    }

    @Override
    public Integer addstarttime(Integer id, String st_ip, String st_start_time) {
        UpdateWrapper<TsAns> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id)
                .set("st_ip",st_ip)
                .set("st_start_time",st_start_time)
                .set("st_status_id",2);
        int update = baseMapper.update(null, updateWrapper);
        return update;
    }
}
