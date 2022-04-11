package cn.race.teacher.mapper;

import cn.race.teacher.output.OutStudents;
import cn.race.teacher.pojo.Student;
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
public interface StudentMapper extends BaseMapper<Student> {
    String querySql  = "SELECT a.id aid,a.pr_id,a.st_id,b.avatar,b.username,b.city,b.email,b.id,b.phone,b.sex,b.statu FROM student a LEFT JOIN sys_user b on a.st_id = b.id";
    String wrapperSql = "SELECT * from ( " + querySql + " ) AS q ${ew.customSqlSegment}";

    String querySqlout = "SELECT a.user_id,a.role_id,b.id,b.avatar,b.city,b.email,b.phone,b.sex,b.statu,b.username FROM sys_user_role a left JOIN sys_user b on a.user_id = b.id where a.role_id = 8";
    String wrapperSqlout = "SELECT * from ( " + querySqlout + " ) AS q ${ew.customSqlSegment}";

    @Select(wrapperSql)
    Page<OutStudents> selectstudentin(Page page, @Param("ew") Wrapper queryWrapper);

    @Select(wrapperSqlout)
    Page<OutStudents> selectstudentout(Page page, @Param("ew") Wrapper queryWrapper);
}
