package cn.race.teacher.controller;

import cn.race.common.response.Result;
import cn.race.teacher.output.OutExams;
import cn.race.teacher.service.ITsPublicService;
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
@RequestMapping("/teacher/tsPublic")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TsPublicController {

    @Autowired
    private ITsPublicService tsPublicService;

    @GetMapping("/selectexams")
    public Result selectExams(@RequestParam Integer pr_id, @RequestParam Integer page, @RequestParam Integer size){
        Page<OutExams> outExamsPage = new Page<>(page,size);
        outExamsPage = tsPublicService.selectexams(outExamsPage,pr_id);
//        return R.ok().message("查询成功").data("exams",outExamsPage);
        return Result.succ("查询成功",outExamsPage);
}

    @PostMapping("/deleteexam")
    public Result deleteExam(@RequestParam Integer pub_id){
        boolean b = tsPublicService.removeById(pub_id);
        if(b) {
//            return R.ok().message("删除成功");
            return Result.succ("删除成功",null);
        }
//        return R.error().message("删除失败");
        return Result.fail("删除失败");
    }




}
