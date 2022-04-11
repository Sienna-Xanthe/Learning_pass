package cn.race.teacher.service.impl;

import cn.race.teacher.pojo.TsPaper;
import cn.race.teacher.mapper.TsPaperMapper;
import cn.race.teacher.service.ITsPaperService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
public class TsPaperServiceImpl extends ServiceImpl<TsPaperMapper, TsPaper> implements ITsPaperService {

    @Override
    public Integer updateTsName(Integer id, String newname) {
        UpdateWrapper<TsPaper> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id)
                .set("ts_name",newname);
        int update = baseMapper.update(null, updateWrapper);
        return update;
    }
}
