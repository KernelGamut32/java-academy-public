package gts.spring.employees.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@MappedSuperclass
public abstract class Employee extends BaseEntity {
    private String name;
    private String jobTitle;

    public abstract BigDecimal calculateWeeklyPay();
}
