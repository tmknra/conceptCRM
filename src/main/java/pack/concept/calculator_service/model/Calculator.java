package pack.concept.calculator_service.model;

import org.springframework.stereotype.Component;
import pack.concept.calculator_service.dto.in.ElectroAcousticInDto;
import pack.concept.calculator_service.dto.in.WireSectionInDto;
import pack.concept.calculator_service.dto.out.ResultValue;

import java.util.ArrayList;

import static java.lang.Math.*;

@Component
public class Calculator {
    /*
    1) Получаем поля калькулятора
    2) По типу калькулятора определяем необходимый расчет
    3) Проводим расчет и помещаем результат в мапу ключ-название, значение-результат расчета
    * */
    public static ArrayList<ResultValue> calculate(ElectroAcousticInDto EAInDto) {
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

        switch (EAInDto.getSpeakerType()) {
            case 1 -> speakerVoicedArea = (long) (PI * pow(
                    ((EAInDto.getCeilingHeight() - 1.5) * tan((double) EAInDto.getOdw() / 2))
                    , 2));
            case 2 -> speakerVoicedArea = (long)
                    (EAInDto.getOdw() * (PI * speakerEffectiveLength * speakerEffectiveLength) / 360);
            case 3 -> speakerVoicedArea = (long)
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
        result.add(new ResultValue("Площадь помещения (S, м2)", roomArea));
        result.add(new ResultValue("Звуковое давление громкоговорителя (P, Дб)", speakerPressure));
        result.add(new ResultValue("Звуковое давление в расчетной точке (P, Дб)", pointPower));
        result.add(new ResultValue("Эффективная дальность громкоговорителя (L, м)", speakerEffectiveLength));
        result.add(new ResultValue("Площадь озвучиваемая громкоговорителем (S, м2)", speakerVoicedArea));
        result.add(new ResultValue("Требуемое количество громкоговорителей", speakersCount));

        return result;
    }

    public static ArrayList<ResultValue> calculate(WireSectionInDto wireSectionInDto) {
        return new ArrayList<>();
    }
}
