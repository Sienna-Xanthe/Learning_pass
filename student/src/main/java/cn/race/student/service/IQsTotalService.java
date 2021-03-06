package cn.race.student.service;

import cn.race.student.dto.QsTotalDto;
import cn.race.student.pojo.QsTotal;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
public interface IQsTotalService extends IService<QsTotal> {
    QsTotalDto selectpaperdetail(Integer qsId);
}
