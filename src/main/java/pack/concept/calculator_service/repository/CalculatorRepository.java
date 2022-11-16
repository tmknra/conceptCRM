package pack.concept.calculator_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pack.concept.calculator_service.model.CalculatorEntity;

@Repository
public interface CalculatorRepository extends JpaRepository<CalculatorEntity, Long> {
}
