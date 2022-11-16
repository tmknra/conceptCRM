package pack.concept.calculator_service.model;

import org.springframework.stereotype.Component;
import pack.concept.calculator_service.dto.in.ElectroAcousticInDto;
import pack.concept.calculator_service.dto.in.WireSectionInDto;
import pack.concept.calculator_service.dto.out.ResultValue;

import java.math.BigDecimal;
import java.math.BigInteger;
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
        Long roomArea,
                pointPower,
                speakersCount;
        Double speakerPressure,
                speakerEffectiveLength,
                speakerVoicedArea = 1.0;

        roomArea = EAInDto.getRoomLength() * EAInDto.getRoomWidth();
        speakerPressure = EAInDto.getSpl() + 10 *
                log10(EAInDto.getSpeakerPower());
        pointPower = (long) Math.ceil(speakerPressure - 20 * log10(EAInDto.getCalculatedPointDistance()));

        double p = speakerPressure - (EAInDto.getNoisePower() + EAInDto.getSoundPressure());
        speakerEffectiveLength = pow(10, p / 20);
        System.out.println(speakerEffectiveLength);
        switch (EAInDto.getSpeakerType()) {
            case 1 -> speakerVoicedArea = (3.14 *
                    ((EAInDto.getCeilingHeight() - 1.5) * tan(Math.toRadians((double) EAInDto.getOdw() / 2))) *
                    ((EAInDto.getCeilingHeight() - 1.5) * tan(Math.toRadians((double) EAInDto.getOdw() / 2))));
            case 2 -> speakerVoicedArea =
                    (EAInDto.getOdw() * (3.14 * speakerEffectiveLength * speakerEffectiveLength) / 360);
            case 3 -> speakerVoicedArea =
                    (PI * speakerEffectiveLength / 2 * speakerEffectiveLength / 2 *
                            tan(Math.toRadians((double) EAInDto.getOdw() / 2)));
        }
        speakersCount = (long) Math.ceil((double) roomArea / speakerVoicedArea);

        // return ElectroAcousticOutDto.builder()
        //         .roomArea(roomArea)
        //         .speakerPressure(speakerPressure)
        //         .pointPower(pointPower)
        //         .speakerEffectiveLength(speakerEffectiveLength)
        //         .speakerVoicedArea(speakerVoicedArea)
        //         .speakersCount(speakersCount)
        //         .build();
        result.add(new ResultValue("Площадь помещения (S, м2)", roomArea));
        result.add(new ResultValue("Звуковое давление громкоговорителя (P, Дб)",
                ((Double) Math.ceil(speakerPressure)).longValue()));
        result.add(new ResultValue("Звуковое давление в расчетной точке (P, Дб)", pointPower));
        result.add(new ResultValue("Эффективная дальность громкоговорителя (L, м)",
                speakerEffectiveLength.longValue()));
        result.add(new ResultValue("Площадь озвучиваемая громкоговорителем (S, м2)",
                ((Double) Math.ceil(speakerVoicedArea)).longValue()));
        result.add(new ResultValue("Требуемое количество громкоговорителей", speakersCount));

        return result;
    }

    public static ArrayList<ResultValue> calculate(WireSectionInDto wireSectionInDto) {
        return new ArrayList<>();
    }
}
