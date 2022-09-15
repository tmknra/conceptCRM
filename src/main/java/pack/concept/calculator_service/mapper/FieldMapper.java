package pack.concept.calculator_service.mapper;

import org.mapstruct.Mapper;
import pack.concept.calculator_service.dto.out.FieldOutDto;
import pack.concept.calculator_service.model.FieldEntity;

@Mapper(componentModel = "spring")
public abstract class FieldMapper {

    public abstract FieldOutDto fieldToFieldOutDto(FieldEntity field);

}
