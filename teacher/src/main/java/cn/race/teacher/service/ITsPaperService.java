package cn.race.teacher.service;

import cn.race.teacher.pojo.TsPaper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
public interface ITsPaperService extends IService<TsPaper> {

    Integer updateTsName(Integer id, String newname);
}
