package cn.race.teacher.service.impl;

import cn.race.teacher.pojo.Project;
import cn.race.teacher.mapper.ProjectMapper;
import cn.race.teacher.service.IProjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    @Override
    public int addProject(Project project) {
        int insert = baseMapper.insert(project);
        return insert;
    }

    @Override
    public Page<Project> selectByTcId(Integer tcId, String projectname, Integer page, Integer size) {
        QueryWrapper<Project> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Project::getTcId,tcId)
                .like(Project::getPrName, projectname);
        Page<Project> page1 = new Page<>(page,size);
        baseMapper.selectPage(page1,queryWrapper);
        return page1;
    }

    @Override
    public int deleteById(Integer prId) {
        int ans = baseMapper.deleteById(prId);
        return ans;
    }
}
