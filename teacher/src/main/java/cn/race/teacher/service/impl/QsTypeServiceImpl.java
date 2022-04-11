package cn.race.teacher.service.impl;

import cn.race.teacher.pojo.QsType;
import cn.race.teacher.mapper.QsTypeMapper;
import cn.race.teacher.service.IQsTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class QsTypeServiceImpl extends ServiceImpl<QsTypeMapper, QsType> implements IQsTypeService {

    @Override
    public List<QsType> selecttypes() {
        List<QsType> list = this.list();
        return list;
    }
}
