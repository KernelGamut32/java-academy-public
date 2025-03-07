package gts.spring.employees.dao.jpa;

import gts.spring.employees.domain.SalariedEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface SalariedEmployeeDAO extends JpaRepository<SalariedEmployee, Long> {
public interface SalariedEmployeeDAO extends CrudRepository<SalariedEmployee, Long> {
}
