package cn.race.student.convert;

import cn.race.student.dto.SysUserDto;
import cn.race.student.pojo.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;



@Mapper
public interface SysUserConvert {
    SysUserConvert INSTANCE= Mappers.getMapper(SysUserConvert.class);

    SysUserDto entity2dto(SysUser entity);

    SysUser dto2entity(SysUserDto dto);
}
