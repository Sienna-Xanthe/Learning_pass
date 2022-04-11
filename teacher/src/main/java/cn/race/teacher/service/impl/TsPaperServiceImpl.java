package cn.race.teacher.service.impl;

import cn.race.common.MD5.MD5Util;
import cn.race.common.response.BusinessException;
import cn.race.common.response.CommonErrorCode;
import cn.race.teacher.convertor.TsPaperConvertor;
import cn.race.teacher.dto.TsPaperDto;
import cn.race.teacher.mapper.TsDetailsMapper;
import cn.race.teacher.pojo.TsPaper;
import cn.race.teacher.mapper.TsPaperMapper;
import cn.race.teacher.service.ITsPaperService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-03-30
 */
@Service
public class TsPaperServiceImpl extends ServiceImpl<TsPaperMapper, TsPaper> implements ITsPaperService {

    @Autowired
    TsPaperMapper tsPaperMapper;


    @Override
    public Integer addpaper(Integer id,TsPaper tsPaper) {
        tsPaper.setTcId(id);
        tsPaperMapper.insert(tsPaper);
        Integer ids = tsPaper.getId();
        return ids;
    }

    @Override
    public Integer deletepaper(Integer[] paperIds) {
        int i = tsPaperMapper.deleteBatchIds(Arrays.asList(paperIds));
        return i;
    }

    @Override
    public List<TsPaper> selectByPr(Integer projectId, String tsName) {
        LambdaQueryWrapper<TsPaper> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsPaper::getPrId,projectId).like(StringUtils.isNotBlank(tsName),TsPaper::getTsName,tsName);
        List<TsPaper> tsPapers = tsPaperMapper.selectList(lambdaQueryWrapper);
        return tsPapers;
    }

    @Override
    public List<TsPaper> selectbyrecycle(Integer projectId) {
        List<TsPaper> selectbyrecycle = tsPaperMapper.selectbyrecycle(projectId);
        return selectbyrecycle;
    }

    @Override
    public void bankbyrecycle(Integer paperId) {
     tsPaperMapper.bankbyrecycle(paperId);

    }

    @Override
    public Integer sealpaper(Integer paperId, String password) {
        TsPaper tsPaper = new TsPaper();
        tsPaper.setId(paperId);
        tsPaper.setTsPassword(MD5Util.encode(password));
        int i = tsPaperMapper.updateById(tsPaper);
        return i;
    }

    @Override
    public void nosealpaper(Integer paperId, String password) {
        LambdaQueryWrapper<TsPaper> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsPaper::getId,paperId);
        TsPaper tsPaper = tsPaperMapper.selectOne(lambdaQueryWrapper);
        boolean equals = tsPaper.getTsPassword().equals(MD5Util.encode(password));
        if(!equals){
            throw new BusinessException(CommonErrorCode.PASSWORD_NO);
        }
        if(equals){
            TsPaper tsPaper1=new TsPaper();
            tsPaper1.setTsPassword("");
            tsPaperMapper.update(tsPaper1,lambdaQueryWrapper);
        }

    }

    @Override
    public Integer forgetpass(Integer paperId, String newPassword) {
        LambdaQueryWrapper<TsPaper> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsPaper::getId,paperId);
        TsPaper tsPaper = new TsPaper();
        tsPaper.setTsPassword(MD5Util.encode(newPassword));
        int update = tsPaperMapper.update(tsPaper, lambdaQueryWrapper);
        return update;
    }


    @Override
    public TsPaperDto selectById(Integer paperId) {
        LambdaQueryWrapper<TsPaper> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsPaper::getId,paperId);
        TsPaper tsPaper = tsPaperMapper.selectOne(lambdaQueryWrapper);
        return TsPaperConvertor.INSTANCE.entity2Dto(tsPaper);
    }

    @Override
    public String selectPassword(Integer paperId) {
        LambdaQueryWrapper<TsPaper> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TsPaper::getId,paperId);
        String tsPassword = tsPaperMapper.selectOne(lambdaQueryWrapper).getTsPassword();
        return tsPassword;
    }

}
