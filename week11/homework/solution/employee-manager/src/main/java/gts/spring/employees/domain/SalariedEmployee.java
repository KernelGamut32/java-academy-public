package gts.spring.employees.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class SalariedEmployee extends Employee {
    private double yearlySalary;

    @Override
    @JsonGetter("weeklyPay")
    public BigDecimal calculateWeeklyPay() {
        return new BigDecimal(yearlySalary / 52).setScale(2, RoundingMode.HALF_UP);
    }
}
