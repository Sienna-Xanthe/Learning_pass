package cn.race.teacher.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutStudents {
    private Long id;

    private String username;

    private String avatar;

    private String email;

    private String city;

    private Date lastLogin;

    private Integer statu;

    private String phone;

    private String sex;
}
