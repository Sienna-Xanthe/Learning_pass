package cn.race.student.controller;

import cn.race.common.response.Result;
import cn.race.student.output.OutExam;
import cn.race.student.service.ITsAnsService;
import cn.race.student.service.ITsPublicService;
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
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/student/tsAns")
public class TsAnsController {
    @Autowired
    private ITsAnsService iTsAnsService;

    @Autowired
    ITsPublicService iTsPublicService;

    /**
     * 查看考试
     */
    @GetMapping("/selectexam")
    public Result selectExam(@RequestParam Integer st_id, @RequestParam Integer pr_id , @RequestParam Integer st_status_id, @RequestParam Integer page, @RequestParam Integer size){
        Page<OutExam> outExamPage = new Page<>(page,size);
        outExamPage = iTsAnsService.selectExams(outExamPage,st_id,pr_id,st_status_id);
//        return R.ok().message("查询成功").data("exam",outExamPage);
        return Result.succ("查询成功",outExamPage);

    }

    /**
     * 进入考试获取语音播报
     */
    @PostMapping("/inputexam")
    public Result inputexam(Integer pubId){
        String s = iTsPublicService.selectSing(pubId);
        if(s != null)
        {
//            return R.ok().message("更新成功");
            return Result.succ("查询成功",s);
        }
//        return R.ok().message("更新失败");
        return Result.fail("查询失败，没有数据");
    }

    /**
     * 开始考试存储信息
     */
    @PostMapping("/addstarttime")
    public Result addStartTime(@RequestParam Integer id, @RequestParam String st_ip, @RequestParam String st_start_time){
        Integer updete = iTsAnsService.addstarttime(id,st_ip,st_start_time);
        if(updete != 0)
        {
//            return R.ok().message("更新成功");
            return Result.succ("更新成功",null);
        }
//        return R.ok().message("更新失败");
        return Result.fail("更新失败");
    }





}
