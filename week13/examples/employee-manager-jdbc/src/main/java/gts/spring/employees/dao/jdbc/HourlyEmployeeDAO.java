package gts.spring.employees.dao.jdbc;

import gts.spring.employees.dao.BaseDAO;
import gts.spring.employees.domain.HourlyEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class HourlyEmployeeDAO implements BaseDAO<HourlyEmployee> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean update(HourlyEmployee hourlyEmployee) {
        String sql = """
            UPDATE hourlyEmployee SET
                name = ?,
                jobTitle = ?,
                hoursWorked = ?,
                hourlyPayRate = ?
            WHERE id = ?
        """;
        var numRows = jdbcTemplate.update(sql, hourlyEmployee.getName(), hourlyEmployee.getJobTitle(),
                hourlyEmployee.getHoursWorked(), hourlyEmployee.getHourlyPayRate(), hourlyEmployee.getId());
        return numRows == 1;
    }

    @Override
    public boolean delete(HourlyEmployee hourlyEmployee) {
        String sql = """
            DELETE FROM hourlyEmployee WHERE id = ?
        """;
        var numRows = jdbcTemplate.update(sql, hourlyEmployee.getId());
        return numRows == 1;
    }

    @Override
    public HourlyEmployee insert(HourlyEmployee hourlyEmployee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = """
            INSERT INTO hourlyEmployee (name, jobTitle, hoursWorked, hourlyPayRate)
                VALUES (?, ?, ?, ?)
        """;
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
            ps.setString(1, hourlyEmployee.getName());
            ps.setString(2, hourlyEmployee.getJobTitle());
            ps.setDouble(3, hourlyEmployee.getHoursWorked());
            ps.setDouble(4, hourlyEmployee.getHourlyPayRate());
            return ps;
        }, keyHolder);
        hourlyEmployee.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return hourlyEmployee;
    }

    @Override
    public Optional<HourlyEmployee> findById(Long id) {
        String sql = """
            SELECT id, name, jobTitle, hoursWorked, hourlyPayRate
            FROM hourlyEmployee WHERE id = ?
        """;
        var results = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(HourlyEmployee.class), id);
        if (results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.getFirst());
    }

    @Override
    public List<HourlyEmployee> findAll() {
        String sql = """
            SELECT id, name, jobTitle, hoursWorked, hourlyPayRate
            FROM hourlyEmployee
        """;
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(HourlyEmployee.class));
    }
}
