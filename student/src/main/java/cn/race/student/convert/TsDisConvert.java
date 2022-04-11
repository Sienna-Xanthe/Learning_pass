package cn.race.student.convert;


import cn.race.student.dto.TsDisDto;
import cn.race.student.pojo.TsDis;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TsDisConvert {
    TsDisConvert INSTANCE= Mappers.getMapper(TsDisConvert.class);

    List<TsDisDto> entity2Dto(List<TsDis> tsDis);
}
