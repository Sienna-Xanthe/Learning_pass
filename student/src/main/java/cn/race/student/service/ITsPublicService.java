package cn.race.student.service;

import cn.race.student.pojo.TsPublic;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
public interface ITsPublicService extends IService<TsPublic> {
Integer selectByPubId(Integer pubId);
}
