package cn.race.teacher.service;

import cn.race.teacher.pojo.Project;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-29
 */
public interface IProjectService extends IService<Project> {

    int addProject(Project project);

    Page<Project> selectByTcId(Integer tcId, String projectname, Integer page, Integer size);

    int deleteById(Integer prId);
}
