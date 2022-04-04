package cn.race.student;

import cn.race.feign.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@MapperScan("cn.race.student.mapper")
@EnableFeignClients(basePackages = "cn.race.feign.clients",defaultConfiguration = DefaultFeignConfiguration.class)
public class StudentApplication {

    public static void main(String[] args) {

        SpringApplication.run(StudentApplication.class, args);
    }

}
