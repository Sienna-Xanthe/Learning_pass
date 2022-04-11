package cn.race.teacher.convertor;

import cn.race.teacher.dto.QsTotalDto;
import cn.race.teacher.pojo.QsTotal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface QsTotalConvert {


    QsTotalConvert INSTANCE= Mappers.getMapper(QsTotalConvert.class);

    QsTotalDto entity2Dto(QsTotal qsTotal);

}
