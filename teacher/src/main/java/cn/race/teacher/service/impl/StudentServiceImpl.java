package cn.race.teacher.service.impl;

import cn.race.teacher.pojo.Student;
import cn.race.teacher.mapper.StudentMapper;
import cn.race.teacher.service.IStudentService;
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

}
