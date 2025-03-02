package gts.spring.employees.dao.inmemory;

import gts.spring.employees.domain.HourlyEmployee;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryHourlyEmployeeDAO extends InMemoryEmployeeDAO<HourlyEmployee> {
}
