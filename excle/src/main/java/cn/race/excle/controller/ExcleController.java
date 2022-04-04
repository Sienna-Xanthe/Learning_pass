package cn.race.excle.controller;


import cn.race.common.response.Result;
import cn.race.excle.pojo.Team;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel/")
@CrossOrigin
public class ExcleController {
    @PostMapping("readExcel")
    public Result simpleRead(@RequestParam MultipartFile file) throws IOException {
        List list = new ArrayList();
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, Team.class, new PageReadListener<Team>(dataList -> {
            for (Team team : dataList) {
                System.out.println(JSON.toJSONString(team));
                list.add(team);
            }
        })).sheet().doRead();
        return  Result.succ("导入成功",list);
    }
}
