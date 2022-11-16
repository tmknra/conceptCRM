package pack.concept.calculator_service.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fields")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class FieldEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calculator_id", nullable = false)
    @ToString.Exclude
    private CalculatorEntity calculator;

    @Column
    private String name;

    @Column
    private String tooltip;

    @Column
    private String code;

    @Column
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        FieldEntity that = (FieldEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
