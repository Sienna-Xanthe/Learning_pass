package cn.race.teacher.service;

import cn.race.teacher.dto.TsPaperDto;
import cn.race.teacher.pojo.TsPaper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
public interface ITsPaperService extends IService<TsPaper> {
    Integer addpaper(Integer id,TsPaper tsPaper);
    Integer deletepaper(Integer[] paperIds);

    List<TsPaper> selectByPr(Integer projectId, String tsName);
    List<TsPaper> selectbyrecycle(Integer projectId);
    void bankbyrecycle(Integer paperId);
    Integer sealpaper(Integer paperId,String password);
    void nosealpaper(Integer paperId,String password);
    Integer forgetpass(Integer paperId,String newPassword);

    TsPaperDto selectById(Integer paperId);

    String selectPassword(Integer paperId);

    Integer updateTsName(Integer id, String newname);
}
