package cn.race.student.convert;



import cn.race.student.dto.TsPaperDto;
import cn.race.student.pojo.TsPaper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TsPaperConvertor {
    TsPaperConvertor INSTANCE= Mappers.getMapper(TsPaperConvertor.class);

    TsPaperDto entity2Dto(TsPaper tsPaper);
}
