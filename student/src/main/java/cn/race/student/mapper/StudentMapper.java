package cn.race.student.mapper;


import cn.race.student.pojo.Project;
import cn.race.student.pojo.Student;
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

    String querySql  = "SELECT a.pr_id,a.st_id,b.id,b.pr_desc,b.pr_name,b.deleted,b.pr_pic,b.pr_teacher,b.tc_id FROM student a LEFT JOIN project b on a.pr_id = b.id";
    String wrapperSql = "SELECT * from ( " + querySql + " ) AS q ${ew.customSqlSegment}";


    @Select(wrapperSql)
    Page<Project> selectproject(Page page, @Param("ew") Wrapper queryWrapper);
}
