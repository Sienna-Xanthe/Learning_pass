package cn.race.teacher.dto;

import lombok.Data;

@Data
public class TsPaperDto {

    //名字
    private String tsName;
    //总分
    private Double tsScore;
    //难易程度
    private Integer levelId;
    //课程id
    private Integer prId;
}
