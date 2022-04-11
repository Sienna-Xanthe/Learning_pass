package cn.race.student.service.impl;

import cn.race.student.mapper.StudentMapper;
import cn.race.student.pojo.Project;
import cn.race.student.pojo.Student;
import cn.race.student.service.IStudentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Override
    public Page<Project> selectProjects(Page<Project> projectPage, Integer st_id, String pr_name) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("st_id",st_id)
                .eq("deleted",0)
                .like("pr_name",pr_name);
        Page<Project> selectproject = baseMapper.selectproject(projectPage, queryWrapper);
        return selectproject;
    }
}
