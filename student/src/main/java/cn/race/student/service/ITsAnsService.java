package cn.race.student.service;

import cn.race.student.pojo.TsAns;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
public interface ITsAnsService extends IService<TsAns> {
    int updateStdAns(Integer pubId,Integer stuId);
    TsAns selectByStuIdAndPubId(Integer pubId,Integer stuId);
}
