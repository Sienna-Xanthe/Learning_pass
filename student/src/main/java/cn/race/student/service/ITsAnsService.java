package cn.race.student.service;

import cn.race.student.output.OutExam;
import cn.race.student.pojo.TsAns;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
public interface ITsAnsService extends IService<TsAns> {

    Page<OutExam> selectExams(Page<OutExam> outExamPage, Integer st_id, Integer pr_id, Integer st_status_id);

    Integer addstarttime(Integer id, String st_ip, String st_start_time);
}
