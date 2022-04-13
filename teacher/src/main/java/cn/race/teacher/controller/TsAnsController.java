package cn.race.teacher.controller;

import cn.race.common.response.Result;
import cn.race.teacher.output.OuTsStudents;
import cn.race.teacher.service.ITsAnsService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pxy
 * @since 2022-04-08
 */
@RestController
@RequestMapping("/teacher/tsAns")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TsAnsController {
    @Autowired
    private ITsAnsService tsAnsService;

    /**
     * 查询考试的学生详细
     * @param pub_id
     * @param st_status_id
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/selectstudents")
    public Result selectStudents(@RequestParam Integer pub_id, @RequestParam Integer st_status_id , @RequestParam Integer page, @RequestParam Integer size){
        Page<OuTsStudents> tsAnsPage = new Page<>(page,size);
        tsAnsPage = tsAnsService.selectStudents(tsAnsPage,pub_id,st_status_id);
//        return R.ok().message("查询成功").data( "students",tsAnsPage);
        return Result.succ("查询成功",tsAnsPage);
    }

    /**
     * 老师将学生打回重做
     */
    @PostMapping("/redo")
    public Result reDo(@RequestParam Integer id){
        Integer update = tsAnsService.redo(id);
//        return R.ok().message("更改成功");
        return Result.succ("更改成功",null);
    }

}
