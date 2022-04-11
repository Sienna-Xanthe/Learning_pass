package cn.race.student.service;

import cn.race.student.pojo.Project;
import cn.race.student.pojo.Student;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
public interface IStudentService extends IService<Student> {

    Page<Project> selectProjects(Page<Project> projectPage, Integer st_id, String pr_name);
}
