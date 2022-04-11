package cn.race.teacher.service.impl;

import cn.race.teacher.mapper.AnsDetailsMapper;
import cn.race.teacher.pojo.AnsDetails;
import cn.race.teacher.service.IAnsDetailsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pxy
 * @since 2022-04-08
 */
@Service
public class AnsDetailsServiceImpl extends ServiceImpl<AnsDetailsMapper, AnsDetails> implements IAnsDetailsService {

    @Override
    public Integer deleteansdetails(List<Integer> delectans) {
        QueryWrapper<AnsDetails> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("ans_id",delectans);
        int delete = baseMapper.delete(queryWrapper);
        return delete;
    }
}
