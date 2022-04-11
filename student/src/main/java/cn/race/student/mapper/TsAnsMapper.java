package cn.race.student.mapper;


import cn.race.student.output.OutExam;
import cn.race.student.pojo.TsAns;
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
public interface TsAnsMapper extends BaseMapper<TsAns> {

    String querySql  = "SELECT a.id,a.pub_id,a.st_id,a.st_status_id,b.id pubid,b.start_time,b.end_time,b.ts_id,b.limit_time,b.status_id,c.id cid, c.ts_name,c.pr_id,c.deleted FROM ts_ans a LEFT JOIN ts_public b on b.id = a.pub_id LEFT JOIN ts_paper c on b.ts_id = c.id";
    String wrapperSql = "SELECT * from ( " + querySql + " ) AS q ${ew.customSqlSegment}";

    @Select(wrapperSql)
    Page<OutExam> selectexams(Page page, @Param("ew") Wrapper queryWrapper);
}
