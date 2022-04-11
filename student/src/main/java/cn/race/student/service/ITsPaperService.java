package cn.race.student.service;

import cn.race.student.dto.TsPaperDto;
import cn.race.student.pojo.TsPaper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
public interface ITsPaperService extends IService<TsPaper> {
    TsPaperDto selectById(Integer paperId);
}
