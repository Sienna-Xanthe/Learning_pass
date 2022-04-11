package cn.race.student.service.impl;

import cn.race.student.mapper.AnsDetailsMapper;
import cn.race.student.pojo.AnsDetails;
import cn.race.student.service.AnsDetailsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnsDetailsServiceImpl implements AnsDetailsService {

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
        lambdaQueryWrapper.eq(AnsDetails::getAnsId,ansId);
        List<AnsDetails> ansDetails = ansDetailsMapper.selectList(lambdaQueryWrapper);
        return ansDetails;
    }
}
