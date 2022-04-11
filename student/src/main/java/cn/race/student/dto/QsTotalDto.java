package cn.race.student.dto;

import lombok.Data;

@Data
public class QsTotalDto {
    private Integer id;
    private Integer typeId;
    private Integer levelId;
    private Integer isSub;
    private String question;
}
