package pack.concept.calculator_service.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pack.concept.calculator_service.dto.in.ElectroAcousticInDto;
import pack.concept.calculator_service.dto.out.CalculatorOutDto;
import pack.concept.calculator_service.dto.out.ElectroAcousticOutDto;
import pack.concept.calculator_service.model.CalculatorEntity;
import pack.concept.calculator_service.payload.request.CalculatorRequest;
import pack.concept.calculator_service.payload.response.CalculatorResponse;

import java.util.List;

public interface CalculatorService {

    List<CalculatorEntity> getAllCalculators();

    CalculatorOutDto getCalculatorById(Long id);

    CalculatorResponse calculateResult(CalculatorRequest request);

    ElectroAcousticOutDto calculateElectroAcoustic(ElectroAcousticInDto electroAcousticInDto);
}
