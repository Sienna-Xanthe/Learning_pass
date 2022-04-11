package cn.race.teacher.mapper;

import cn.race.teacher.output.OuTsStudents;
import cn.race.teacher.pojo.TsAns;
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

    String querySql  = "SELECT a.id,a.app_ip,a.approve_id,a.correct_rate,a.pub_id,a.st_ip,a.st_name,a.st_num,a.st_score,a.st_start_time,a.st_status_id,a.st_submit_time,b.id bid,b.city,b.email,b.phone,b.username,c.id cid,c.status, d.id did, d.approve FROM ts_ans a LEFT JOIN sys_user b ON a.st_id = b.id LEFT JOIN st_status c ON a.st_status_id = c.id LEFT JOIN tc_approve d ON a.approve_id = d.id";
    String wrapperSql = "SELECT * from ( " + querySql + " ) AS q ${ew.customSqlSegment}";//a.total_num,

    @Select(wrapperSql)
    Page<OuTsStudents> selectstudents(Page page, @Param("ew") Wrapper queryWrapper);
}
