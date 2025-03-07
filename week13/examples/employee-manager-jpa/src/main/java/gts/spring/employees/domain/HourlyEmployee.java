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
//@Table(name = "EMPLOYEE_HOURLY")
public class HourlyEmployee extends Employee {
    private double hoursWorked;
    private double hourlyPayRate;

    @Override
    @JsonGetter("weeklyPay")
    public BigDecimal calculateWeeklyPay() {
        return new BigDecimal(hoursWorked * hourlyPayRate).setScale(2, RoundingMode.HALF_UP);
    }

    @JsonGetter("yearlyPay")
    public BigDecimal calculateYearlyPay() {
        return new BigDecimal(hoursWorked * hourlyPayRate * 52).setScale(2, RoundingMode.HALF_UP);
    }
}
