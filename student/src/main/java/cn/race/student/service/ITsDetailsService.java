package cn.race.student.service;

import cn.race.student.pojo.TsDetails;
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
public interface ITsDetailsService extends IService<TsDetails> {
    List<TsDetails> selectpaperdetail(Integer paperId);
}
