package pack.concept.calculator_service.model;

import javax.persistence.*;

@Entity
@Table(name = "calculator_values")
public class CalculatorValue {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long calculator_id;

}
