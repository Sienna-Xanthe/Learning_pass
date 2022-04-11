package cn.race.teacher.controller;

import cn.race.common.response.Result;
import cn.race.teacher.input.InputTs;
import cn.race.teacher.pojo.TsDetails;
import cn.race.teacher.pojo.TsDis;
import cn.race.teacher.pojo.TsPaper;
import cn.race.teacher.service.ITsDetailsService;
import cn.race.teacher.service.ITsDisService;
import cn.race.teacher.service.ITsPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pxy
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/teacher/tsPaper")
public class TsPaperController {

    @Autowired
    private ITsPaperService iTsPaperService;

    @Autowired
    private ITsDisService iTsDisService;

    @Autowired
    private ITsDetailsService iTsDetailsService;

    /**
     * 添加题目
     */
    @PostMapping("/addtspaper")
    public Result addTsPaper(@RequestBody InputTs inputTs) {
        TsPaper tsPaper = inputTs.getTsPaper();
        List<TsDis> disList = inputTs.getDisList();
        List<TsDetails> detailsList = inputTs.getDetailsList();
        boolean save = iTsPaperService.save(tsPaper);
        if(save)
        {
            Integer ts_id = tsPaper.getId();
            disList.forEach(t -> {
                t.setTsId(ts_id);
            });
            boolean b = iTsDisService.saveBatch(disList);
            if(b){
                detailsList.forEach(t -> {
                    t.setTsId(ts_id);
                });
                boolean b1 = iTsDetailsService.saveBatch(detailsList);
                if (b1){
//                    return R.ok().message("添加成功");
                    return Result.succ("添加成功",null);
                }
//                return R.error().message("添加失败");
                return Result.fail("添加失败");
            }
//            return R.error().message("添加失败");
            return Result.fail("添加失败");

        }
//        return R.error().message("添加失败");
        return Result.fail("添加失败");

    }

    /**
     * 更改试卷名称
     */
    @PostMapping("/updatetsname")
    public Result updateTsName(@RequestParam Integer id, @RequestParam String newname) {

        Integer update = iTsPaperService.updateTsName(id, newname);
        if(update != 0){
//            return R.ok().message("更改试卷名称成功");
            return Result.succ("更改试卷名称成功",null);
        }
//        return R.error().message("更改失败");
        return Result.fail("更改失败");
    }
}
