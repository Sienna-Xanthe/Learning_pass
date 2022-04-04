package cn.race.teacher.service.impl;

import cn.race.teacher.pojo.Project;
import cn.race.teacher.mapper.ProjectMapper;
import cn.race.teacher.service.IProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-29
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

}
