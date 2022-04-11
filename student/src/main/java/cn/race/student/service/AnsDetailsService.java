package cn.race.student.service;

import cn.race.student.pojo.AnsDetails;

import java.util.List;

public interface AnsDetailsService {
    int addAnsDetails(Integer ansId,String ans);
    List<AnsDetails> selectList(Integer ansId);
}
