package pack.concept.calculator_service.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultValue {
    private String name;
    private Long value;
}
