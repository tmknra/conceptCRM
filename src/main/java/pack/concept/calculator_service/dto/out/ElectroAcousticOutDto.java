package pack.concept.calculator_service.dto.out;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ElectroAcousticOutDto {
    private Long roomArea;
    private Long speakerPressure;
    private Long pointPower;
    private Long speakerEffectiveLength;
    private Long speakerVoicedArea;
    private Long speakersCount;
}
