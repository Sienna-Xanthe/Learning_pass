package cn.race.teacher.mapper;

import cn.race.teacher.output.OutExams;
import cn.race.teacher.pojo.TsPublic;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
@Repository
public interface TsPublicMapper extends BaseMapper<TsPublic> {
    String querySql  = "SELECT a.id, a.ts_id,a.start_time,a.end_time,a.limit_time,a.status_id,a.total_num,b.id bid,b.ts_name,b.ts_score,b.level_id,b.tc_id,b.pr_id,b.deleted,d.id did,d.username FROM ts_public a LEFT JOIN ts_paper b ON a.ts_id = b.id LEFT JOIN sys_user d ON b.pr_id = d.id";
    String wrapperSql = "SELECT * from ( " + querySql + " ) AS q ${ew.customSqlSegment}";//a.total_num,

    @Select(wrapperSql)
    Page<OutExams> selectexams(Page page, @Param("ew") Wrapper queryWrapper);
}
