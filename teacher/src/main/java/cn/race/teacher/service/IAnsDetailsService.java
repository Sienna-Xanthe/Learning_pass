package cn.race.teacher.service;

import cn.race.teacher.pojo.AnsDetails;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pxy
 * @since 2022-04-08
 */
public interface IAnsDetailsService extends IService<AnsDetails> {

    Integer deleteansdetails(List<Integer> delectans);
}
