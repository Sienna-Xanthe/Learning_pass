package cn.race.teacher.service.impl;

import cn.race.teacher.convertor.TsDisConvert;
import cn.race.teacher.convertor.TsPaperConvertor;
import cn.race.teacher.dto.TsDisDto;
import cn.race.teacher.pojo.TsDis;
import cn.race.teacher.mapper.TsDisMapper;
import cn.race.teacher.service.ITsDisService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class TsDisServiceImpl extends ServiceImpl<TsDisMapper, TsDis> implements ITsDisService {

    @Autowired
    TsDisMapper tsDisMapper;

    @Override
    public int addTsDis(Integer paperId,TsDis tsDis) {
        tsDis.setTsId(paperId);
        int insert = tsDisMapper.insert(tsDis);
        return insert;
    }

    @Override
    public Map<String, Integer> selectCount(Integer paperId) {

        HashMap<String, Integer> map = new HashMap<>();

        //单选题数量
        LambdaQueryWrapper<TsDis> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(TsDis::getTsId,paperId).eq(TsDis::getTypeId,1);
        TsDis tsDis1 = tsDisMapper.selectOne(lambdaQueryWrapper1);
        if(tsDis1!=null) {
            Integer qsCount1 = tsDis1.getQsCount();
            map.put("sign", qsCount1);
        }

        //多选题数量
        LambdaQueryWrapper<TsDis> lambdaQueryWrapper2 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper2.eq(TsDis::getTsId,paperId).eq(TsDis::getTypeId,2);
        TsDis tsDis2 = tsDisMapper.selectOne(lambdaQueryWrapper2);
        if(tsDis2!=null) {
            Integer qsCount2 = tsDis2.getQsCount();
            map.put("max", qsCount2);
        }

        //填空数量
        LambdaQueryWrapper<TsDis> lambdaQueryWrapper3 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper3.eq(TsDis::getTsId,paperId).eq(TsDis::getTypeId,3);
        TsDis tsDis3 = tsDisMapper.selectOne(lambdaQueryWrapper3);
        if(tsDis3!=null){
            Integer qsCount3 = tsDis3.getQsCount();
            map.put("fill",qsCount3);
        }


        //判断题数量
        LambdaQueryWrapper<TsDis> lambdaQueryWrapper4 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper4.eq(TsDis::getTsId,paperId).eq(TsDis::getTypeId,4);
        TsDis tsDis4 = tsDisMapper.selectOne(lambdaQueryWrapper4);
        if(tsDis4!=null) {
            Integer qsCount4 = tsDis4.getQsCount();
            map.put("judge", qsCount4);
        }

        //简答题数量
        LambdaQueryWrapper<TsDis> lambdaQueryWrapper5 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper5.eq(TsDis::getTsId,paperId).eq(TsDis::getTypeId,5);
        TsDis tsDis5 = tsDisMapper.selectOne(lambdaQueryWrapper5);
        if(tsDis5!=null) {
            Integer qsCount5 = tsDis5.getQsCount();
            map.put("answer", qsCount5);
        }

        return map;
    }

    @Override
    public Map<String, Double> selectSc(Integer paperId) {
        HashMap<String, Double> map = new HashMap<>();

        LambdaQueryWrapper<TsDis> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsDis::getTsId,paperId)
                .eq(TsDis::getTypeId,1);
        TsDis tsDis = tsDisMapper.selectOne(lambdaQueryWrapper);
        if(tsDis!=null) {
            map.put("sign", tsDis.getQsSc());
        }

        LambdaQueryWrapper<TsDis> lambdaQueryWrapper2 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper2.eq(TsDis::getTsId,paperId)
                .eq(TsDis::getTypeId,2);
        TsDis tsDis2 = tsDisMapper.selectOne(lambdaQueryWrapper2);
        if(tsDis2!=null) {
            map.put("max", tsDis2.getQsSc());
        }

        LambdaQueryWrapper<TsDis> lambdaQueryWrapper3 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper3.eq(TsDis::getTsId,paperId)
                .eq(TsDis::getTypeId,3);
        TsDis tsDis3 = tsDisMapper.selectOne(lambdaQueryWrapper3);
        if(tsDis3!=null) {
            map.put("fill", tsDis3.getQsSc());
        }

        LambdaQueryWrapper<TsDis> lambdaQueryWrapper4 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper4.eq(TsDis::getTsId,paperId)
                .eq(TsDis::getTypeId,4);
        TsDis tsDis4 = tsDisMapper.selectOne(lambdaQueryWrapper4);
        if(tsDis4!=null) {
            map.put("judge", tsDis4.getQsSc());
        }

        LambdaQueryWrapper<TsDis> lambdaQueryWrapper5 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper5.eq(TsDis::getTsId,paperId)
                .eq(TsDis::getTypeId,5);
        TsDis tsDis5 = tsDisMapper.selectOne(lambdaQueryWrapper5);

        if(tsDis5!=null) {
            map.put("answer", tsDis5.getQsSc());
        }
        return map;
    }

    @Override
    public List<TsDisDto> selectpaperdetail(Integer paperId) {
        LambdaQueryWrapper<TsDis> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsDis::getTsId,paperId);
        List<TsDis> tsDis = tsDisMapper.selectList(lambdaQueryWrapper);
        return TsDisConvert.INSTANCE.entity2Dto(tsDis);
    }
}
