package cn.race.student.service;

import cn.race.student.pojo.QsOp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
public interface IQsOpService extends IService<QsOp> {
    List<QsOp> selectpaperdetail(Integer qsId);
}
