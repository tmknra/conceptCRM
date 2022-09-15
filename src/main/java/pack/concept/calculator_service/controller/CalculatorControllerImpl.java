package pack.concept.calculator_service.controller;

import pack.concept.calculator_service.dto.out.CalculatorOutDto;
import pack.concept.calculator_service.model.CalculatorEntity;
import pack.concept.calculator_service.payload.request.CalculatorRequest;
import pack.concept.calculator_service.payload.response.CalculatorResponse;

import java.util.List;

public class CalculatorControllerImpl implements CalculatorController{
    @Override
    public List<CalculatorEntity> getAllCalculators() {
        return null;
    }

    @Override
    public CalculatorOutDto getCalculatorById(Long id) {
        return null;
    }

    @Override
    public CalculatorResponse calculate(CalculatorRequest request) {
        return null;
    }
}
