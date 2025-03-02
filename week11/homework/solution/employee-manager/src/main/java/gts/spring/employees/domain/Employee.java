package gts.spring.employees.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class Employee extends BaseEntity {
    private String name;
    private String jobTitle;

    public abstract BigDecimal calculateWeeklyPay();
}
