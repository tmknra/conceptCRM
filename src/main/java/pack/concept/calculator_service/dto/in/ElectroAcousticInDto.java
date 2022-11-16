package pack.concept.calculator_service.dto.in;

import lombok.Data;

@Data
public class ElectroAcousticInDto {
    /*чувствительность*/
    private Long spl;
    private Long speakerPower;
    /*ШДН - ширина диаграммы направленности*/
    private Long odw;
    private Long speakerType;
    /*уровень шума в помещении*/
    private Long noisePower;

    private Long ceilingHeight;
    private Long roomLength;
    private Long roomWidth;
    /*Sound pressure - запас по звуковому давлению*/
    private Long soundPressure;
    private Long calculatedPointDistance;
}
