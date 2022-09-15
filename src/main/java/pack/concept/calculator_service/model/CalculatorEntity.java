package pack.concept.calculator_service.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "calculators")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CalculatorEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "calculator",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<FieldEntity> fields;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        CalculatorEntity that = (CalculatorEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Object calculate(List<FieldEntity> fields){

        return null;
    }
}
