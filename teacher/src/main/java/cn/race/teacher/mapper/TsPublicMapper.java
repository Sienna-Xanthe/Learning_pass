package cn.race.teacher.mapper;

import cn.race.teacher.output.OutExams;
import cn.race.teacher.output.OutGrage;
import cn.race.teacher.pojo.TsPublic;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
public interface TsPublicMapper extends BaseMapper<TsPublic> {
    String querySql  = "SELECT a.id, a.ts_id,a.start_time,a.end_time,a.limit_time,a.status_id,a.total_num,b.id bid,b.ts_name,b.ts_score,b.level_id,b.tc_id,b.pr_id,b.deleted,d.id did,d.username FROM ts_public a LEFT JOIN ts_paper b ON a.ts_id = b.id LEFT JOIN sys_user d ON b.pr_id = d.id";
    String wrapperSql = "SELECT * from ( " + querySql + " ) AS q ${ew.customSqlSegment}";//a.total_num,

    @Select(wrapperSql)
    Page<OutExams> selectexams(Page page, @Param("ew") Wrapper queryWrapper);

    @Select("select a.st_name,a.st_score,t.ts_name,p.id from ts_public as p \n" +
            "inner join ts_ans as a on a.pub_id=p.id \n" +
            "inner join ts_paper as t on t.id=p.ts_id \n" +
            "where a.st_score is NOT NULL")
    List<OutGrage> selectAllGrage();


    @Select("select a.st_name,a.st_score,t.ts_name,p.id from ts_public as p \n" +
            "inner join ts_ans as a on a.pub_id=p.id \n" +
            "inner join ts_paper as t on t.id=p.ts_id \n" +
            "where a.st_score is NOT NULL\n" +
            "and a.st_name LIKE '%${name}%' ")
    List<OutGrage> showcardbyname(String name);

    @Select("select a.st_name,a.st_score,t.ts_name,p.id from ts_public as p \n" +
            "inner join ts_ans as a on a.pub_id=p.id \n" +
            "inner join ts_paper as t on t.id=p.ts_id \n" +
            "where a.st_score is NOT NULL\n" +
            "and t.ts_name LIKE '%${tsName}%' ")
    List<OutGrage> showcardbyTs(String tsName);

}
