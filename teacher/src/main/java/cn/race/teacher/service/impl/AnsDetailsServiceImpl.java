package cn.race.teacher.service.impl;


import cn.race.teacher.mapper.AnsDetailsMapper;
import cn.race.teacher.pojo.AnsDetails;
import cn.race.teacher.service.IAnsDetailsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnsDetailsServiceImpl extends ServiceImpl<AnsDetailsMapper, AnsDetails> implements IAnsDetailsService {

    @Autowired
    AnsDetailsMapper ansDetailsMapper;

    @Override
    public int addAnsDetails(Integer ansId, String ans) {
        AnsDetails ansDetails = new AnsDetails();
        ansDetails.setAnsId(ansId);
        ansDetails.setAns(ans);
        int insert = ansDetailsMapper.insert(ansDetails);
        return insert;
    }

    @Override
    public List<AnsDetails> selectList(Integer ansId) {
        LambdaQueryWrapper<AnsDetails> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AnsDetails::getAnsId, ansId);
        List<AnsDetails> ansDetails = ansDetailsMapper.selectList(lambdaQueryWrapper);
        return ansDetails;
    }

    @Override
    public Integer deleteansdetails(List<Integer> delectans) {
        QueryWrapper<AnsDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("ans_id",delectans);
        int delete = baseMapper.delete(queryWrapper);
        return delete;
    }
}
