package cn.race.teacher.service;

import cn.race.teacher.output.OutStudents;
import cn.race.teacher.pojo.Student;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
public interface IStudentService extends IService<Student> {
List<Student> selectBypr(Integer prId);

    Page<OutStudents> selectstudent(String name, Integer method, Integer pr_id, Integer page, Integer size);
}
