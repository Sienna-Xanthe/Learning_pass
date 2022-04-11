package cn.race.teacher.service;

import cn.race.teacher.pojo.TsDetails;
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
int addTsDetails(Integer paperId,Integer prId);
 List<TsDetails> selectpaperdetail(Integer paperId);
}
