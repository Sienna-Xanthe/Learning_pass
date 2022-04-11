package cn.race.teacher.service.impl;

import cn.race.teacher.pojo.Student;
import cn.race.teacher.mapper.StudentMapper;
import cn.race.teacher.service.IStudentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
