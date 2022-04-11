package cn.race.teacher.service;

import cn.race.teacher.output.OutExams;
import cn.race.teacher.pojo.TsPublic;
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
public interface ITsPublicService extends IService<TsPublic> {

    Page<OutExams> selectexams(Page<OutExams> outExamsPage, Integer pr_id);
}
