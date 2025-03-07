package gts.spring.employees.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
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
