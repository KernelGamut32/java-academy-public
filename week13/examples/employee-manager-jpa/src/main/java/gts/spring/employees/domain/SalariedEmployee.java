package gts.spring.employees.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
//@Table(name = "EMPLOYEE_SALARIED")
public class SalariedEmployee extends Employee {
    private double yearlySalary;

    @Override
    @JsonGetter("weeklyPay")
    public BigDecimal calculateWeeklyPay() {
        return new BigDecimal(yearlySalary / 52).setScale(2, RoundingMode.HALF_UP);
    }
}
