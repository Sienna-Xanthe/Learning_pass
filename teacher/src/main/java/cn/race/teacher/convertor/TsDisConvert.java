package cn.race.teacher.convertor;

import cn.race.teacher.dto.TsDisDto;
import cn.race.teacher.pojo.TsDis;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TsDisConvert {
    TsDisConvert INSTANCE= Mappers.getMapper(TsDisConvert.class);

    List<TsDisDto> entity2Dto(List<TsDis> tsDis);
}
