package cn.race.teacher.service.impl;

import cn.race.teacher.output.OutStudents;
import cn.race.teacher.pojo.Student;
import cn.race.teacher.mapper.StudentMapper;
import cn.race.teacher.service.IStudentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<Student> selectBypr(Integer prId) {
        LambdaQueryWrapper<Student> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Student::getPrId,prId);
        List<Student> students = studentMapper.selectList(lambdaQueryWrapper);
        return students;
      }
    @Override
    public Page<OutStudents> selectstudent(String name, Integer method, Integer pr_id, Integer page, Integer size) {
        //查询本班级所在学生
        if(method == 1){
            QueryWrapper<OutStudents> studentsQueryWrapper = new QueryWrapper<>();
            studentsQueryWrapper.eq("pr_id",pr_id)
                    .like("username",name);
            Page<OutStudents> pagea = new Page<>(page,size);
            Page<OutStudents> page1 = baseMapper.selectstudentin(pagea, studentsQueryWrapper);
            return page1;
        }
        //查询不在这个班级的学生
        else if(method == 2) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("pr_id",pr_id);
            List<Student> studentList = baseMapper.selectByMap(map);
            List<Integer> ids = new ArrayList<>();
            studentList.forEach(student -> {
                ids.add(student.getStId());
            });
            QueryWrapper<OutStudents> queryWrapper = new QueryWrapper<>();
            queryWrapper
                    .notIn("id",ids)
                    .like("username",name);
            Page<OutStudents> pagea = new Page<>(page,size);
            Page<OutStudents> selectstudentout = baseMapper.selectstudentout(pagea, queryWrapper);
            return selectstudentout;
        }
        return null;
    }
}
