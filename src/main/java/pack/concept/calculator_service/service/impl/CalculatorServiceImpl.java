package pack.concept.calculator_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pack.concept.calculator_service.dto.out.CalculatorOutDto;
import pack.concept.calculator_service.mapper.CalculatorMapper;
import pack.concept.calculator_service.model.CalculatorEntity;
import pack.concept.calculator_service.payload.request.CalculatorRequest;
import pack.concept.calculator_service.payload.response.CalculatorResponse;
import pack.concept.calculator_service.repository.CalculatorRepository;
import pack.concept.calculator_service.service.CalculatorService;

import java.util.List;
import java.util.Optional;

public class CalculatorServiceImpl implements CalculatorService {

    @Autowired
    private CalculatorRepository calculatorRepository;
    @Autowired
    private CalculatorMapper calculatorMapper;

    @Override
    public List<CalculatorEntity> getAllCalculators() {
        return calculatorRepository.findAll();
    }

    @Override
    public CalculatorOutDto getCalculatorById(Long id) {
        Optional<CalculatorEntity> byId = calculatorRepository.findById(id);
        if (byId.isEmpty())
            throw new RuntimeException("wrong id or calculator does not exist");
        return calculatorMapper.calculatorToCalculatorOutDto(byId.get());
    }

    @Override
    public CalculatorResponse calculateResult(CalculatorRequest request) {
        return calculate(request);
    }

    private CalculatorResponse calculate(CalculatorRequest request) {

        return null;
    }
}
