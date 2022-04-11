package cn.race.teacher.controller;

import cn.race.common.response.Result;
import cn.race.teacher.output.OutStudents;
import cn.race.teacher.pojo.Student;
import cn.race.teacher.service.IStudentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pxy
 * @since 2022-04-07
 */
@RestController
@RequestMapping("/teacher/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    /**
     * 查询学生
     */
    @GetMapping("/selectstudent")
    public Result selectStudent(@RequestParam String name, @RequestParam Integer method, @RequestParam Integer pr_id, @RequestParam Integer page, @RequestParam Integer size) {
        Page<OutStudents> studentList = studentService.selectstudent(name, method, pr_id, page, size);
//        return R.ok().message("查询成功").data("studentList",studentList);
        return Result.succ("查询成功",studentList);
    }

    /**
     * 查询后添加学生到该班级
     */
    @PostMapping("/addstudent")
    public Result addStudent(@RequestBody Student student){
//        System.out.println(student.getPrId());
        boolean save = studentService.save(student);
        if(save) {
//            return R.ok().message("插入成功");
            return Result.succ("插入成功",null);
        }
//        return R.error().message("插入失败");
        return Result.fail("插入失败");
    }


}
