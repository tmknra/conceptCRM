package pack.concept.calculator_service.model;

import org.springframework.stereotype.Component;
import pack.concept.calculator_service.dto.in.ElectroAcousticInDto;
import pack.concept.calculator_service.dto.out.ElectroAcousticOutDto;

import static java.lang.Math.*;

@Component
public class Calculator {
        /*
        1) Получаем поля калькулятора
        2) По типу калькулятора определяем необходимый расчет
        3) Проводим расчет и помещаем результат в мапу ключ-название, значение-результат расчета
        * */
    public static ElectroAcousticOutDto calculateElectroAcoustic(ElectroAcousticInDto EAInDto) {
        Long roomArea;
        Long speakerPressure;
        Long pointPower;
        Long speakerEffectiveLength;
        Long speakerVoicedArea = 1L;
        Long speakersCount;

        roomArea = EAInDto.getRoomLength() * EAInDto.getRoomWidth();
        speakerPressure = (long) (EAInDto.getSPL() + 10 * log(EAInDto.getSpeakerPower()));
        pointPower = (long) (speakerPressure - 20 * log(EAInDto.getCalculatedPointDistance()));

        speakerEffectiveLength = (long) pow(
                10,
                ((speakerPressure - (EAInDto.getNoisePower() + EAInDto.getSoundPressure())) / 20));

        if (EAInDto.getSpeakerType() == 1L) {
            speakerVoicedArea = (long) (PI * pow(
                    ((EAInDto.getCeilingHeight() - 1.5) * tan(EAInDto.getODW() / 2))
                    , 2));
        } else if (EAInDto.getSpeakerType() == 2L) {
            speakerVoicedArea = (long) (EAInDto.getODW() * (PI * speakerEffectiveLength * speakerEffectiveLength) / 360);
        } else if (EAInDto.getSpeakerType() == 3L) {
            speakerVoicedArea = (long)
                    (PI * speakerEffectiveLength / 2 * speakerEffectiveLength / 2 * tan(EAInDto.getODW() / 2));
        }
        speakersCount = roomArea / speakerVoicedArea;

        return ElectroAcousticOutDto.builder()
                .roomArea(roomArea)
                .speakerPressure(speakerPressure)
                .pointPower(pointPower)
                .speakerEffectiveLength(speakerEffectiveLength)
                .speakerVoicedArea(speakerVoicedArea)
                .speakersCount(speakersCount)
                .build();
    }

    public static void calculateWireSection(){
    };
}
