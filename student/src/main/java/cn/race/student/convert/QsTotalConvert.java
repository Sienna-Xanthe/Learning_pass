package cn.race.student.convert;


import cn.race.student.dto.QsTotalDto;
import cn.race.student.pojo.QsTotal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QsTotalConvert {


    QsTotalConvert INSTANCE= Mappers.getMapper(QsTotalConvert.class);

    QsTotalDto entity2Dto(QsTotal qsTotal);

}
