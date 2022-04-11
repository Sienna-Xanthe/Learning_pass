package cn.race.teacher.service;

import cn.race.teacher.pojo.StdAns;
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
public interface IStdAnsService extends IService<StdAns> {
    List<StdAns> selectDe(Integer pubId, Integer stuId);
    List<Integer> delectans(Integer stId, Integer pubId);
}
