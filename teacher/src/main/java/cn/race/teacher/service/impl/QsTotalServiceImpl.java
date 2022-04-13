package cn.race.teacher.service.impl;

import cn.race.teacher.convertor.QsTotalConvert;
import cn.race.teacher.dto.QsTotalDto;
import cn.race.teacher.pojo.QsTotal;
import cn.race.teacher.mapper.QsTotalMapper;
import cn.race.teacher.service.IQsTotalService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.race.teacher.input.InputSlctQs;
import cn.race.teacher.output.OutQsTotal;
import cn.race.teacher.output.OutTotal;
import cn.race.teacher.pojo.QsTotal;
import cn.race.teacher.mapper.QsTotalMapper;
import cn.race.teacher.service.IQsTotalService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    QsTotalMapper qsTotalMapper;

    @Override
    public Map<String, List<Integer>> selectQsByPr(Integer prId) {

        Map<String, List<Integer>> map = new HashMap<>();

        //单选题
        LambdaQueryWrapper<QsTotal> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        List<Integer> list1 = new ArrayList<>();
        lambdaQueryWrapper1.eq(QsTotal::getPrId,prId)
                .eq(QsTotal::getTypeId,1);
        List<QsTotal> qsTotals1 = qsTotalMapper.selectList(lambdaQueryWrapper1);
       for(QsTotal qsTotal:qsTotals1){
           Integer id = qsTotal.getId();
           list1.add(id);
       }
       map.put("sign",list1);

       //多选题
        LambdaQueryWrapper<QsTotal> lambdaQueryWrapper2 = new LambdaQueryWrapper<>();
        List<Integer> list2 = new ArrayList<>();
        lambdaQueryWrapper2.eq(QsTotal::getPrId,prId)
                .eq(QsTotal::getTypeId,2);
        List<QsTotal> qsTotals2 = qsTotalMapper.selectList(lambdaQueryWrapper2);
        for(QsTotal qsTotal:qsTotals2){
            Integer id = qsTotal.getId();
            list2.add(id);
        }
        map.put("max",list2);

        //填空题
        LambdaQueryWrapper<QsTotal> lambdaQueryWrapper3 = new LambdaQueryWrapper<>();
        List<Integer> list3 = new ArrayList<>();
        lambdaQueryWrapper3.eq(QsTotal::getPrId,prId)
                .eq(QsTotal::getTypeId,3);
        List<QsTotal> qsTotals3 = qsTotalMapper.selectList(lambdaQueryWrapper3);
        for(QsTotal qsTotal:qsTotals3){
            Integer id = qsTotal.getId();
            list3.add(id);
        }
        map.put("fill",list3);

        //判断题
        LambdaQueryWrapper<QsTotal> lambdaQueryWrapper4 = new LambdaQueryWrapper<>();
        List<Integer> list4 = new ArrayList<>();
        lambdaQueryWrapper4.eq(QsTotal::getPrId,prId)
                .eq(QsTotal::getTypeId,4);
        List<QsTotal> qsTotals4 = qsTotalMapper.selectList(lambdaQueryWrapper4);
        for(QsTotal qsTotal:qsTotals4){
            Integer id = qsTotal.getId();
            list4.add(id);
        }
        map.put("judge",list4);

        //简答题
        LambdaQueryWrapper<QsTotal> lambdaQueryWrapper5 = new LambdaQueryWrapper<>();
        List<Integer> list5 = new ArrayList<>();
        lambdaQueryWrapper5.eq(QsTotal::getPrId,prId)
                .eq(QsTotal::getTypeId,5);
        List<QsTotal> qsTotals5 = qsTotalMapper.selectList(lambdaQueryWrapper5);
        for(QsTotal qsTotal:qsTotals5){
            Integer id = qsTotal.getId();
            list5.add(id);
        }
        map.put("answer",list5);


       return map;
    }

    @Override
    public Map<String, Integer> selectQsCount(Integer prId) {

        Map<String,Integer> map = new HashMap<>();

        //单选题
        LambdaQueryWrapper<QsTotal> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(QsTotal::getPrId,prId)
                .eq(QsTotal::getTypeId,1);
        List<QsTotal> qsTotals1 = qsTotalMapper.selectList(lambdaQueryWrapper1);
        map.put("sign",qsTotals1.size());

        //多选题
        LambdaQueryWrapper<QsTotal> lambdaQueryWrapper2 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper2.eq(QsTotal::getPrId,prId)
                .eq(QsTotal::getTypeId,2);
        List<QsTotal> qsTotals2 = qsTotalMapper.selectList(lambdaQueryWrapper2);
        map.put("max",qsTotals2.size());

        //填空题
        LambdaQueryWrapper<QsTotal> lambdaQueryWrapper3 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper3.eq(QsTotal::getPrId,prId)
                .eq(QsTotal::getTypeId,3);
        List<QsTotal> qsTotals3 = qsTotalMapper.selectList(lambdaQueryWrapper3);
        map.put("fill",qsTotals3.size());

        //判断题
        LambdaQueryWrapper<QsTotal> lambdaQueryWrapper4 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper4.eq(QsTotal::getPrId,prId)
                .eq(QsTotal::getTypeId,4);
        List<QsTotal> qsTotals4 = qsTotalMapper.selectList(lambdaQueryWrapper4);
        map.put("judge",qsTotals4.size());

        //简答题
        LambdaQueryWrapper<QsTotal> lambdaQueryWrapper5 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper5.eq(QsTotal::getPrId,prId)
                .eq(QsTotal::getTypeId,5);
        List<QsTotal> qsTotals5 = qsTotalMapper.selectList(lambdaQueryWrapper5);
        map.put("answer",qsTotals5.size());


        return map;
    }

    @Override
    public QsTotalDto selectpaperdetail(Integer qsId) {
        LambdaQueryWrapper<QsTotal> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(QsTotal::getId, qsId);
        QsTotal qsTotal = qsTotalMapper.selectOne(lambdaQueryWrapper);
        return QsTotalConvert.INSTANCE.entity2Dto(qsTotal);
    }
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
