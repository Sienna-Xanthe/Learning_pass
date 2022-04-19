package cn.race.teacher.controller;

import cn.race.common.response.Result;
import cn.race.teacher.output.CustomerInfoExcel;
import cn.race.teacher.output.OutGrage;
import cn.race.teacher.service.ITsPublicService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.nacos.client.naming.utils.RandomUtils;
//import com.sun.deploy.net.URLEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/teacher/analyze")
@CrossOrigin(origins = "*",maxAge = 3600)
public class AnalyzeController {

    @Autowired
    ITsPublicService iTsPublicService;

    /**
     * 显示所有的成绩分析统计
     */
    @GetMapping("/showallcard")
    public Result showallcard() {
        List<OutGrage> showallcard = iTsPublicService.showallcard();
        return Result.succ("查询成功",showallcard);
    }

    /**
     * 按名字查询
     */
    @GetMapping("/showcardbyname")
    public Result showcardbyname(String stuName) {
        List<OutGrage> showcardbyname = iTsPublicService.showcardbyname(stuName);
        return Result.succ("查询成功",showcardbyname);
    }

    /**
     * 按试卷名字查询
     */
    @GetMapping("/showcardbyTs")
    public Result showcardbyTs(String tsName) {
        List<OutGrage> outGrages = iTsPublicService.showcardbyTs(tsName);
        return Result.succ("查询成功",outGrages);
    }

    /**
     * 对成绩升序排序
     */
    @PostMapping("/gradeup")
    public Result gradeup(@RequestBody List<OutGrage> outGrages) {
        List<OutGrage> newList = outGrages.stream().sorted(Comparator.comparing(OutGrage::getStScore))
                .collect(Collectors.toList());
        return Result.succ("查询成功",newList);
    }

    /**
     * 对成绩降序排序
     */
    @PostMapping("/gradedown")
    public Result gradedown(@RequestBody List<OutGrage> outGrages) {
        List<OutGrage> newList = outGrages.stream().sorted(Comparator.comparing(OutGrage::getStScore).reversed())
                .collect(Collectors.toList());
        return Result.succ("查询成功",newList);
    }

    /**
     * 导出成绩到excle
     * @return
     */
//    @RequestMapping(value = "/export")
//    public Result exportExcel(HttpServletResponse response,@RequestBody List<OutGrage> outGrage) {
//        try {
//            String fileName = "成绩导出_" + RandomUtils.nextInt(3)+ ".xlsx";
////            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
//            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
////            response.setContentType("text/xml");
//            response.setContentType("application/vnd.ms-excel");
//            //导出excel
//            EasyExcel.write(response.getOutputStream(), CustomerInfoExcel.class).sheet("学生成绩统计表").doWrite(outGrage);
//            return Result.succ("成绩导出成功");
//        } catch (Exception e) {
//            return Result.succ("成绩导出异常");
//        } finally {
//            try {
//                response.flushBuffer();
//            } catch (IOException e) {
//                log.error("业务订单导出输出流关闭失败: {}", e);
//            }
//        }
//    }


        @RequestMapping(value = "/export")
    public void exportExcel(HttpServletResponse response,@RequestBody List<OutGrage> outGrage) {
            try {
                response.setContentType("application/vnd.ms-excel");
                response.setCharacterEncoding("utf-8");
                String fileName = URLEncoder.encode("成绩", "UTF-8");
                response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
                EasyExcel.write(response.getOutputStream(), CustomerInfoExcel.class).sheet("模板").doWrite(outGrage);
            } catch (Exception e) {
                log.error("下载文件失败，失败原因{}", e.getMessage());
                // 重置response
                response.reset();
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                try {
                    response.getWriter().println("错误");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        }
}
