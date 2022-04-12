package cn.race.teacher.mapper;


import cn.race.teacher.output.OutQsTotal;
import cn.race.teacher.output.OutTotal;
import cn.race.teacher.pojo.QsTotal;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
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
public interface QsTotalMapper extends BaseMapper<QsTotal> {
    String querySql  = "SELECT a.id,a.type_id,a.level_id,a.pr_id,a.tc_id,a.question,a.deleted,b.id bid,b.type,c.id cid,c.level,d.id did,d.username from qs_total a left join qs_type b on a.type_id = b.id LEFT JOIN qs_level c on a.level_id = c.id LEFT JOIN sys_user d on a.tc_id = d.id";
    String wrapperSql = "SELECT * from ( " + querySql + " ) AS q ${ew.customSqlSegment}";

    @Select("SELECT * from qs_total a left join qs_type b on a.type_id = b.id LEFT JOIN qs_level c on a.level_id = c.id")
    Page<OutQsTotal> test(Page<OutQsTotal> iPage);

    @Select(wrapperSql)
    Page<OutQsTotal> selectname(Page page, @Param("ew") Wrapper queryWrapper);

    @Select(wrapperSql)
    OutTotal selectByQsId(@Param("ew") Wrapper queryWrapper);
}
