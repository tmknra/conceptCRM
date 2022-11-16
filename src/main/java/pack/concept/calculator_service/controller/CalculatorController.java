package pack.concept.calculator_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pack.concept.calculator_service.dto.in.ElectroAcousticInDto;
import pack.concept.calculator_service.dto.out.CalculatorOutDto;
import pack.concept.calculator_service.dto.out.ElectroAcousticOutDto;
import pack.concept.calculator_service.model.CalculatorEntity;
import pack.concept.calculator_service.payload.request.CalculatorRequest;
import pack.concept.calculator_service.payload.response.CalculatorResponse;

import java.util.List;

@RequestMapping("/calc")
public interface CalculatorController {

    @GetMapping("/all")
    List<CalculatorEntity> getAllCalculators();

    @GetMapping("/calc/{id}")
    CalculatorOutDto getCalculatorById(@PathVariable Long id);

    @PostMapping("/{id}/calculate")
    CalculatorResponse calculate(@PathVariable Long id, @RequestBody CalculatorRequest request);

    @PostMapping("/ea")
    ElectroAcousticOutDto calculateElectroAcoustic(@RequestBody ElectroAcousticInDto electroAcousticInDto);
}
