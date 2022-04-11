package cn.race.teacher.service;

import cn.race.teacher.dto.QsTotalDto;
import cn.race.teacher.pojo.QsTotal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
public interface IQsTotalService extends IService<QsTotal> {

    Map<String, List<Integer>> selectQsByPr(Integer prId);

    Map<String,Integer> selectQsCount(Integer prId);

    QsTotalDto selectpaperdetail(Integer qsId);
}
