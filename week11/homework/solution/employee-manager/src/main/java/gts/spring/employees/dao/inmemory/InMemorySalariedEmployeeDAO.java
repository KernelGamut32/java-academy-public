package gts.spring.employees.dao.inmemory;

import gts.spring.employees.domain.SalariedEmployee;
import org.springframework.stereotype.Repository;

@Repository
public class InMemorySalariedEmployeeDAO extends InMemoryEmployeeDAO<SalariedEmployee> {
}
