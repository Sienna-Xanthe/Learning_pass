package cn.race.student.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;

public class Test {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://162.14.113.138:3306/exam?useSSL=false&useUnicode=true&characterEncoding=utf-8", "exam", "7nDNsJKiYSa2NkWE")
                .globalConfig(builder -> {
                    builder
//                            .author("baomidou") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .dateType(DateType.ONLY_DATE)//配置日期

                            .outputDir("C://Users//win10//Desktop//Learning_pass//student//src//main//java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.race") // 设置父包名
                            .moduleName("student") // 设置父包模块名mapperXml
                            .entity("pojo")
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, "C://Users//win10//Desktop//Learning_pass//student//src//main//java//cn//race//student//mapper"))
                .pathInfo(Collections.singletonMap(OutputFile.xml,"C://Users//win10//Desktop//Learning_pass//student//src//main//resources//mapper"));

                })
                .strategyConfig(builder -> {
                    builder.addInclude("project","qs_level","qs_op","qs_total","qs_type","st_status","std_ans","student","sys_menu","sys_role","sys_role_menu","sys_user","sys_user_role","tc_approve","ts_ans","ts_details","ts_dis","ts_paper","ts_public","ts_status"); // 设置需要生成的表名
//                            .addTablePrefix("jm_"); // 设置过滤表前缀
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok()//开启lombok
                            .logicDeletePropertyName("deleted")
                            .addTableFills(Arrays.asList(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("update_time",FieldFill.INSERT_UPDATE)
                            ))
                            .enableTableFieldAnnotation();
//                            .logicDeleteColumnName("deleted");
                })

                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }

}
