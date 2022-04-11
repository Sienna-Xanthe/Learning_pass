package cn.race.teacher.service;

import cn.race.teacher.dto.TsDisDto;
import cn.race.teacher.pojo.TsDis;
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
public interface ITsDisService extends IService<TsDis> {
int addTsDis(Integer paperId,TsDis tsDis);

Map<String,Integer> selectCount(Integer paperId);
Map<String,Double> selectSc(Integer paperId);
List<TsDisDto> selectpaperdetail(Integer paperId);
}
