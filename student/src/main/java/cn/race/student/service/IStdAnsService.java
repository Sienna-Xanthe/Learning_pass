package cn.race.student.service;

import cn.race.student.pojo.StdAns;
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
int addStdAns(Integer pubId,Integer stuId,StdAns stdAns);
List<StdAns> selectDe(Integer pubId,Integer stuId);
}
