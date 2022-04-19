package cn.race.teacher.controller;

import cn.race.common.response.Result;
import cn.race.teacher.input.InputQs;
import cn.race.teacher.input.InputSlctQs;
import cn.race.teacher.output.OutQsTotal;
import cn.race.teacher.output.OutTotal;
import cn.race.teacher.pojo.QsLevel;
import cn.race.teacher.pojo.QsOp;
import cn.race.teacher.pojo.QsTotal;
import cn.race.teacher.pojo.QsType;
import cn.race.teacher.service.IQsLevelService;
import cn.race.teacher.service.IQsOpService;
import cn.race.teacher.service.IQsTotalService;
import cn.race.teacher.service.IQsTypeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pxy
 * @since 2022-03-30
 */
@RestController
@RequestMapping("/teacher/qsTotal")
@CrossOrigin(origins = "*",maxAge = 3600)
public class QsTotalController {
    @Autowired
    private IQsTotalService iQsTotalService;

    @Autowired
    private IQsOpService iQsOpService;

    @Autowired
    private IQsLevelService iQsLevelService;

    @Autowired
    private IQsTypeService iQsTypeService;

    /**
     * 添加题目
     */
    @PostMapping("/addquestion")
    public Result addQuestion(@RequestBody InputQs inputQs) {
        QsTotal qsTotal = inputQs.getQsTotal();
        List<QsOp> qsOps = inputQs.getQsOp();
        int ans = iQsTotalService.addQsTotal(qsTotal);
        if(ans != 0){
            int qs_id = qsTotal.getId();
            qsOps.forEach(t -> {
                t.setQsId(qs_id);
            });
            boolean b = iQsOpService.addQsOp(qsOps);
            if(b){
//                return R.ok().message("添加成功");
                return Result.succ("添加成功",null);
            }
//            return R.error().message("添加失败");
            return Result.fail("添加失败");
        }
//        return R.error().message("添加失败");
        return Result.fail("添加失败");
    }

    /**
     * 修改题目
     */
    @PostMapping("/udquestion")
    public Result updateQuestion(@RequestBody InputQs inputQs) {
        QsTotal qsTotal = inputQs.getQsTotal();
        List<QsOp> qsOps = inputQs.getQsOp();
        boolean ans = iQsTotalService.saveOrUpdate(qsTotal);
        int qs_id = qsTotal.getId();
        qsOps.forEach(t -> {
            t.setQsId(qs_id);
        });
        HashMap<String, Object> map = new HashMap<>();
        map.put("qs_id",qs_id);
        boolean b = iQsOpService.removeByMap(map);
        boolean b1 = iQsOpService.saveBatch(qsOps);
        if (b1){
            return Result.succ(200,"更新添加成功",qs_id);
        }
//        return R.ok().data("qs_id",qs_id).message("更新添加成功");


//        return R.error().message("更改失败");
        return Result.fail("更改失败");
    }

    /**
     * 删除题目
     */
    @PostMapping("/deletequestion")
    public Result deleteQuestion(@RequestParam Integer id) {
        int ans = iQsTotalService.deletequestion(id);
        if(ans != 0){
            return Result.succ("删除成功",null);
        }
//        return R.ok().message("删除成功");

//        return R.error().message("删除失败");
        return Result.fail("删除失败");
    }

    /**
     * 查询题库
     */
    @PostMapping("/selectquestion")
    public Result selectQuestion(@RequestBody InputSlctQs inputSlctQs) {
        Page<OutQsTotal> page = iQsTotalService.selectByTcId(inputSlctQs);
//        return R.ok().data("page",page).message("查询成功");
        return Result.succ(200,"查询成功",page);
    }

    /**
     * 查询难易度
     */
    @GetMapping("/selectlevel")
    public Result selectLevel() {
        List<QsLevel> qsLevels = iQsLevelService.selectlevels();
//        return R.ok().data("level",qsLevels).message("查询难易度成功");
        return Result.succ(200,"查询难易度成功",qsLevels);
    }

    /**
     * 查询题型
     */
    @GetMapping("/selecttype")
    public Result selectType() {
        List<QsType> qsTypes = iQsTypeService.selecttypes();
//        return R.ok().data("level",qsTypes).message("查询题型成功");
        return Result.succ(200,"查询题型成功",qsTypes);
    }


    /**
     * 联表查询text
     */
    @GetMapping("/text")
    public Result text() {
        Page<OutQsTotal> iPage = new Page<OutQsTotal>(1, 10);
        iPage = iQsTotalService.test(iPage);
//        return R.ok().data("page",iPage);
        return Result.succ(iPage);
    }

    /**
     * 查询整个题目
     */
    @GetMapping("/selecttotal")
    public Result selectTotal(@RequestParam Integer id) {
        OutTotal  outTotal = iQsTotalService.selectTotal(id);
        List<QsOp> opList = iQsOpService.selectByQsId(id);
        outTotal.setOpList(opList);
//        return R.ok().data("question",outTotal).message("查询题目成功");
        return Result.succ(200,"查询题目成功",outTotal);
    }








}
