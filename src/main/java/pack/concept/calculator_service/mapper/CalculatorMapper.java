package pack.concept.calculator_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import pack.concept.calculator_service.dto.in.CalculatorInDto;
import pack.concept.calculator_service.dto.out.CalculatorOutDto;
import pack.concept.calculator_service.dto.out.FieldOutDto;
import pack.concept.calculator_service.model.CalculatorEntity;
import pack.concept.calculator_service.model.FieldEntity;

@Mapper(componentModel = "spring")
public abstract class CalculatorMapper {

    @Autowired
    private FieldMapper fieldMapper;

    @Mapping(target = "id", ignore = true)
    public abstract CalculatorEntity calculatorInDtoToCalculator(CalculatorInDto calculatorInDto);

    public abstract CalculatorOutDto calculatorToCalculatorOutDto(CalculatorEntity calculator);

    FieldOutDto fieldOutDto(FieldEntity field){
     return fieldMapper.fieldToFieldOutDto(field);
    }
}
