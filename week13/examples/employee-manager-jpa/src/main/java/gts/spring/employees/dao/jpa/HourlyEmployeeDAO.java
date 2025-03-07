package gts.spring.employees.dao.jpa;

import gts.spring.employees.domain.HourlyEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface HourlyEmployeeDAO extends JpaRepository<HourlyEmployee, Long> {
public interface HourlyEmployeeDAO extends CrudRepository<HourlyEmployee, Long> {
}
