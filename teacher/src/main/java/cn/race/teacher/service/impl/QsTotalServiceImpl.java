package cn.race.teacher.service.impl;

import cn.race.teacher.input.InputSlctQs;
import cn.race.teacher.output.OutQsTotal;
import cn.race.teacher.output.OutTotal;
import cn.race.teacher.pojo.QsTotal;
import cn.race.teacher.mapper.QsTotalMapper;
import cn.race.teacher.service.IQsTotalService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
@Service
public class QsTotalServiceImpl extends ServiceImpl<QsTotalMapper, QsTotal> implements IQsTotalService {

    @Override
    public int addQsTotal(QsTotal qsTotal) {
        int insert = baseMapper.insert(qsTotal);
        return insert;
    }

    @Override
    public int deletequestion(Integer id) {
        int i = baseMapper.deleteById(id);
        return i;
    }

    @Override
    public Page<OutQsTotal> selectByTcId(InputSlctQs inputSlctQs) {
        Integer prId = inputSlctQs.getPrId();
        Integer typeId = inputSlctQs.getTypeId();
        Integer levelId = inputSlctQs.getLevelId();
        Integer page1 = inputSlctQs.getPage();
        Integer size = inputSlctQs.getSize();
        String question = inputSlctQs.getQuestion();
        QueryWrapper<QsTotal> queryWrapper=new QueryWrapper<>();

        HashMap<String, Object> map = new HashMap<>();
        map.put("type_id",typeId);
        map.put("level_id",levelId);
        map.put("pr_id",prId);
        queryWrapper.allEq(map,false)
                .eq("deleted",0)
                .like("question",question)
                .orderByAsc("id");
        Page<OutQsTotal> page = new Page<>(page1, size);
//        baseMapper.selectPage(page,queryWrapper);
        Page<OutQsTotal> res = this.baseMapper.selectname(page,queryWrapper);

        return res;
    }

    @Override
    public Page<OutQsTotal> test(Page<OutQsTotal> iPage) {
        baseMapper.test(iPage);
        return iPage;
    }

    @Override
    public OutTotal selectTotal(Integer id) {
        QueryWrapper<QsTotal> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        OutTotal outTotal = this.baseMapper.selectByQsId(queryWrapper);
        return outTotal;
    }
}
