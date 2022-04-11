package cn.race.teacher.convertor;


import cn.race.teacher.dto.TsPaperDto;
import cn.race.teacher.pojo.TsPaper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TsPaperConvertor {
    TsPaperConvertor INSTANCE= Mappers.getMapper(TsPaperConvertor.class);

    TsPaperDto entity2Dto(TsPaper tsPaper);
}
