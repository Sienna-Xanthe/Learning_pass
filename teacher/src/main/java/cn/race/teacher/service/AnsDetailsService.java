package cn.race.teacher.service;



import cn.race.teacher.pojo.AnsDetails;

import java.util.List;

public interface AnsDetailsService {
    int addAnsDetails(Integer ansId,String ans);
    List<AnsDetails> selectList(Integer ansId);
}
