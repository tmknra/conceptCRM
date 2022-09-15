package pack.concept.calculator_service.service;

import org.springframework.stereotype.Service;
import pack.concept.calculator_service.dto.out.CalculatorOutDto;
import pack.concept.calculator_service.model.CalculatorEntity;
import pack.concept.calculator_service.payload.request.CalculatorRequest;
import pack.concept.calculator_service.payload.response.CalculatorResponse;

import java.util.List;

@Service
public interface CalculatorService {

    List<CalculatorEntity> getAllCalculators();

    CalculatorOutDto getCalculatorById(Long id);

    CalculatorResponse calculateResult(CalculatorRequest request);

}
