package cn.race.teacher.controller;

import cn.race.common.response.Result;
import cn.race.teacher.pojo.Project;
import cn.race.teacher.service.IProjectService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pxy
 * @since 2022-03-29
 */
@RestController
@RequestMapping("/teacher/project")
@CrossOrigin(origins = "*",maxAge = 3600)
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    /**
     * 添加课程
     * @param project
     * @return
     */
    @PostMapping("/addproject")
    public Result addProject(@RequestBody Project project) {
            int ans = projectService.addProject(project);
        return ans > 0 ? Result.succ(200,"添加成功",ans) : Result.fail("添加失败");
//        return ans > 0 ? R.ok().data("ans",ans) : R.error().data("ans",null);
    }

    /**
     * 查询老师课程
     * @param tcId 老师id
     * @param projectname 课程名称
     * @param page 第几页
     * @param size 一页多少个
     *
     * @return
     */
    @GetMapping("/selectbytcid")
    public Result getProjects(@RequestParam Integer tcId,@RequestParam String projectname,@RequestParam Integer page,@RequestParam Integer size) {
        Page<Project> projects = projectService.selectByTcId(tcId,projectname,page,size);
        return Result.succ(200,"查询成功",projects);
//        return R.ok().data("projects",projects);

    }

    /**
     * 删除课程
     */
    @PostMapping("/deletebyid")
    public Result deleteById(@RequestParam Integer prId) {
        int ans = projectService.deleteById(prId);
        return ans > 0 ? Result.succ(200,"删除成功",ans) : Result.fail("删除失败");
//        return ans > 0 ? R.ok().data("ans",ans) : R.error().data("ans",null);
    }



}
