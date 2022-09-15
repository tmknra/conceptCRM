package pack.concept.calculator_service.controller;

import org.springframework.web.bind.annotation.*;
import pack.concept.calculator_service.dto.out.CalculatorOutDto;
import pack.concept.calculator_service.model.CalculatorEntity;
import pack.concept.calculator_service.payload.request.CalculatorRequest;
import pack.concept.calculator_service.payload.response.CalculatorResponse;

import java.util.List;

@RestController
@RequestMapping("/calc")
public interface CalculatorController {

    @GetMapping("/all")
    List<CalculatorEntity> getAllCalculators();

    @GetMapping("/calc/{id}")
    CalculatorOutDto getCalculatorById(@PathVariable Long id);

    @PostMapping("/calculate")
    CalculatorResponse calculate(@RequestBody CalculatorRequest request);

}
