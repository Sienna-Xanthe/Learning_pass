package cn.race.teacher.mapper;

import cn.race.teacher.pojo.TsPaper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
@Repository
public interface TsPaperMapper extends BaseMapper<TsPaper> {
@Select("select  * from ts_paper where deleted=1 and pr_id=#{projectId}")
List<TsPaper> selectbyrecycle(Integer projectId);

@Select("update ts_paper set deleted=0 where id=#{paperId}")
void bankbyrecycle(Integer paperId);
}
