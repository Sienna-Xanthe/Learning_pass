package cn.race.teacher.service.impl;

import cn.race.common.MD5.MD5Util;
import cn.race.common.response.BusinessException;
import cn.race.common.response.CommonErrorCode;

import cn.race.teacher.mapper.SysRoleMapper;
import cn.race.teacher.mapper.SysUserMapper;
import cn.race.teacher.pojo.SysRole;
import cn.race.teacher.pojo.SysUser;
import cn.race.teacher.service.SysRoleService;
import cn.race.teacher.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysRoleMapper sysRoleMapper;



//
//    @Override
//    public SysUserDto login(SysUserDto sysUserDto) {
//        SysUser sysUser = SysUserConvert.INSTANCE.dto2entity(sysUserDto);
//        String encode = MD5Util.encode(sysUser.getPassword());
//
//        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(SysUser::getPassword,encode)
//                          .eq(SysUser::getUsername,sysUser.getUsername());
//        SysUser sysUser1 = sysUserMapper.selectOne(lambdaQueryWrapper);
//
//        if(sysUser1==null){
//            throw new BusinessException(CommonErrorCode.USER_NOTEXIST);
//        }
//
//        List<SysRole> sysRoles = sysRoleService.listRolesByUserId(sysUser1.getId());
//       sysUser1.setSysRoles(sysRoles);
//        List<SysRole> sysRoles1 = sysUser1.getSysRoles();
//        System.out.println(sysRoles1);
//        return SysUserConvert.INSTANCE.entity2dto(sysUser1);
//    }

    @Override
    public SysUser login(String username, String password) {
        String encode = MD5Util.encode(password);
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getPassword,encode)
                .eq(SysUser::getUsername,username);
        SysUser sysUser = sysUserMapper.selectOne(lambdaQueryWrapper);
        if(sysUser==null){
            throw new BusinessException(CommonErrorCode.USER_NOTEXIST);
        }
        List<SysRole> sysRoles = sysRoleService.listRolesByUserId(sysUser.getId());
        sysUser.setSysRoles(sysRoles);

        return sysUser;
    }

    @Override
    public String selectMail(Integer id) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getId,id);
        SysUser sysUser = sysUserMapper.selectOne(lambdaQueryWrapper);
        return sysUser.getEmail();
    }

    @Override
    public String selectName(Integer id) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysUser::getId,id);
        SysUser sysUser = sysUserMapper.selectOne(lambdaQueryWrapper);
        return sysUser.getUsername();
    }
}
