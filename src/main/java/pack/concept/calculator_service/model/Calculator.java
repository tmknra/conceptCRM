package pack.concept.calculator_service.model;

import org.springframework.stereotype.Component;
import pack.concept.calculator_service.dto.in.ElectroAcousticInDto;
import pack.concept.calculator_service.dto.out.ElectroAcousticOutDto;
import pack.concept.calculator_service.dto.out.ResultValue;

import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.*;

@Component
public class Calculator {
    /*
    1) Получаем поля калькулятора
    2) По типу калькулятора определяем необходимый расчет
    3) Проводим расчет и помещаем результат в мапу ключ-название, значение-результат расчета
    * */
    public static ArrayList<ResultValue> calculateElectroAcoustic(ElectroAcousticInDto EAInDto) {
        ArrayList<ResultValue> result = new ArrayList<>();
        Long    roomArea,
                speakerPressure,
                pointPower,
                speakerEffectiveLength,
                speakersCount,
                speakerVoicedArea = 1L;

        roomArea = EAInDto.getRoomLength() * EAInDto.getRoomWidth();
        speakerPressure = (long) (EAInDto.getSpl() + 10 * log(EAInDto.getSpeakerPower()));
        pointPower = (long) (speakerPressure - 20 * log(EAInDto.getCalculatedPointDistance()));

        speakerEffectiveLength = (long) pow(
                10,
                (double) (speakerPressure - EAInDto.getNoisePower() - EAInDto.getSoundPressure()) / 20);

        if (EAInDto.getSpeakerType() == 1L) {
            speakerVoicedArea = (long) (PI * pow(
                    ((EAInDto.getCeilingHeight() - 1.5) * tan((double) EAInDto.getOdw() / 2))
                    , 2));
        } else if (EAInDto.getSpeakerType() == 2L) {
            speakerVoicedArea = (long) (EAInDto.getOdw() * (PI * speakerEffectiveLength * speakerEffectiveLength) / 360);
        } else if (EAInDto.getSpeakerType() == 3L) {
            speakerVoicedArea = (long)
                    (PI * speakerEffectiveLength / 2 * speakerEffectiveLength / 2 * tan((double) EAInDto.getOdw() / 2));
        }
        speakersCount = roomArea / speakerVoicedArea;

        // return ElectroAcousticOutDto.builder()
        //         .roomArea(roomArea)
        //         .speakerPressure(speakerPressure)
        //         .pointPower(pointPower)
        //         .speakerEffectiveLength(speakerEffectiveLength)
        //         .speakerVoicedArea(speakerVoicedArea)
        //         .speakersCount(speakersCount)
        //         .build();
        result.add(new ResultValue("roomArea", roomArea));
        result.add(new ResultValue("speakerPressure", speakerPressure));
        result.add(new ResultValue("pointPower", pointPower));
        result.add(new ResultValue("speakerEffectiveLength", speakerEffectiveLength));
        result.add(new ResultValue("speakerVoicedArea", speakerVoicedArea));
        result.add(new ResultValue("speakersCount", speakersCount));

        return result;
    }

    public static void calculateWireSection() {
    }
}
