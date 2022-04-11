package cn.race.teacher.service.impl;

import cn.race.teacher.pojo.TsDetails;
import cn.race.teacher.mapper.TsDetailsMapper;
import cn.race.teacher.service.IQsTotalService;
import cn.race.teacher.service.ITsDetailsService;
import cn.race.teacher.service.ITsDisService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
public class TsDetailsServiceImpl extends ServiceImpl<TsDetailsMapper, TsDetails> implements ITsDetailsService {

    @Autowired
    ITsDisService iTsDisService;

    @Autowired
    IQsTotalService iQsTotalService;

    @Autowired
    TsDetailsMapper tsDetailsMapper;

    @Override
    public int addTsDetails(Integer paperId,Integer prId) {

        Map<String, Integer> disMap = iTsDisService.selectCount(paperId);
        Map<String, List<Integer>> qsMap = iQsTotalService.selectQsByPr(prId);
        Map<String, Double> scMp = iTsDisService.selectSc(paperId);
        //添加单选题
        if(disMap.get("sign")!=null){
            List<Integer> list = qsMap.get("sign");
            Integer sign = disMap.get("sign");
            for(int i=0;i<sign;i++){
                Collections.shuffle(list);
                TsDetails tsDetails = new TsDetails();
                tsDetails.setQsId(list.get(i));
                tsDetails.setTsId(paperId);
                tsDetails.setTypeId(1);
                tsDetails.setQsSc(scMp.get("sign")/sign);
                tsDetailsMapper.insert(tsDetails);
            }
        }

        //添加多选题
        if(disMap.get("max")!=null){
            List<Integer> list = qsMap.get("max");
            Integer max = disMap.get("max");
            for(int i=0;i<max;i++){
                Collections.shuffle(list);
                TsDetails tsDetails = new TsDetails();
                tsDetails.setQsId(list.get(i));
                tsDetails.setTsId(paperId);
                tsDetails.setTypeId(2);
                tsDetails.setQsSc(scMp.get("max")/max);
                tsDetailsMapper.insert(tsDetails);
            }
        }

        //添加填空题
        if(disMap.get("fill")!=null){
            List<Integer> list = qsMap.get("fill");
            Integer fill = disMap.get("fill");
            for(int i=0;i<fill;i++){
                Collections.shuffle(list);
                TsDetails tsDetails = new TsDetails();
                tsDetails.setQsId(list.get(i));
                tsDetails.setTsId(paperId);
                tsDetails.setTypeId(3);
                tsDetails.setQsSc(scMp.get("fill")/fill);
                tsDetailsMapper.insert(tsDetails);
            }
        }

        //添加判断题
        if(disMap.get("judge")!=null){
            List<Integer> list = qsMap.get("judge");
            Integer judge = disMap.get("judge");
            for(int i=0;i<judge;i++){
                Collections.shuffle(list);
                TsDetails tsDetails = new TsDetails();
                tsDetails.setQsId(list.get(i));
                tsDetails.setTsId(paperId);
                tsDetails.setTypeId(4);
                tsDetails.setQsSc(scMp.get("judge")/judge);
                tsDetailsMapper.insert(tsDetails);
            }
        }

        //添加单选题
        if(disMap.get("answer")!=null){
            List<Integer> list = qsMap.get("answer");
            Integer answer = disMap.get("answer");
            for(int i=0;i<answer;i++){
                Collections.shuffle(list);
                TsDetails tsDetails = new TsDetails();
                tsDetails.setQsId(list.get(i));
                tsDetails.setTsId(paperId);
                tsDetails.setTypeId(5);
                tsDetails.setQsSc(scMp.get("answer")/answer);
                tsDetailsMapper.insert(tsDetails);
            }
        }



        return 0;
    }

    @Override
    public List<TsDetails> selectpaperdetail(Integer paperId) {
        LambdaQueryWrapper<TsDetails> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsDetails::getTsId,paperId);
        List<TsDetails> tsDetails = tsDetailsMapper.selectList(lambdaQueryWrapper);
        return tsDetails;
    }
}
