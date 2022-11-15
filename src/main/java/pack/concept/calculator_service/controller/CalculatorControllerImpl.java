package pack.concept.calculator_service.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import pack.concept.calculator_service.dto.in.ElectroAcousticInDto;
import pack.concept.calculator_service.dto.out.CalculatorOutDto;
import pack.concept.calculator_service.dto.out.ElectroAcousticOutDto;
import pack.concept.calculator_service.model.CalculatorEntity;
import pack.concept.calculator_service.payload.request.CalculatorRequest;
import pack.concept.calculator_service.payload.response.CalculatorResponse;
import pack.concept.calculator_service.service.CalculatorService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CalculatorControllerImpl implements CalculatorController{

    private CalculatorService calculatorService;

    public CalculatorControllerImpl(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @Override
    public List<CalculatorEntity> getAllCalculators() {
        return null;
    }

    @Override
    public CalculatorOutDto getCalculatorById(Long id) {
        return null;
    }

    @Override
    public CalculatorResponse calculate(Long id, CalculatorRequest request) {
        return null;
    }

    @Override
    public ElectroAcousticOutDto calculateElectroAcoustic(ElectroAcousticInDto electroAcousticInDto) {
        return calculatorService.calculateElectroAcoustic(electroAcousticInDto);
    }
}
