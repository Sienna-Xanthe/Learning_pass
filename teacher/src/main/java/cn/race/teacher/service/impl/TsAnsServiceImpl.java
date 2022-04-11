package cn.race.teacher.service.impl;

import cn.race.teacher.output.OuTsStudents;
import cn.race.teacher.pojo.TsAns;
import cn.race.teacher.mapper.TsAnsMapper;
import cn.race.teacher.service.IAnsDetailsService;
import cn.race.teacher.service.IStdAnsService;
import cn.race.teacher.service.ITsAnsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class TsAnsServiceImpl extends ServiceImpl<TsAnsMapper, TsAns> implements ITsAnsService {

    @Autowired
    TsAnsMapper tsAnsMapper;
  @Autowired
  private IStdAnsService stdAnsService;

    @Autowired
    private IAnsDetailsService ansDetailsService;

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
    

    @Override
    public Page<OuTsStudents> selectStudents(Page<OuTsStudents> tsAnsPage, Integer pub_id, Integer st_status_id) {
        QueryWrapper<OuTsStudents> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pub_id", pub_id)
                .eq("st_status_id", st_status_id);
        Page<OuTsStudents> selectstudents = baseMapper.selectstudents(tsAnsPage, queryWrapper);
        return selectstudents;
    }

    @Override
    public Integer redo(Integer id) {
        UpdateWrapper<TsAns> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id)
                .set("st_status_id",1)
                .set("st_start_time",null)
                .set("st_submit_time",null)
                .set("st_ip",null)
                .set("correct_rate",null)
                .set("st_score",null)
                .set("approve_id",1)
                .set("app_ip",null);
        int update = baseMapper.update(null, updateWrapper);
        QueryWrapper<TsAns> queryWrapper = new QueryWrapper<>();
        TsAns tsans = this.getById(id);
        Integer stId = tsans.getStId();
        Integer pubId = tsans.getPubId();

        List<Integer> delectans = stdAnsService.delectans(stId, pubId);
        Integer ansss = ansDetailsService.deleteansdetails(delectans);
        return null;
    }
}
