package cn.race.teacher.output;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
@Data
public class CustomerInfoExcel {

    @ColumnWidth(20) // 定义列宽
    @ExcelProperty(value = {"学生姓名"}, index = 0)
    private String stName;
    @ColumnWidth(20) // 定义列宽
    @ExcelProperty(value = {"学生成绩"}, index = 1)
    private Double stScore;
    @ColumnWidth(20) // 定义列宽
    @ExcelProperty(value = {"试卷名称"}, index = 2)
    private String tsName;
    @ColumnWidth(20) // 定义列宽
    @ExcelProperty(value = {"发行编号"}, index = 3)
    private Integer id;
}
