package gts.spring.employees.dao.jdbc;

import gts.spring.employees.dao.BaseDAO;
import gts.spring.employees.domain.SalariedEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SalariedEmployeeDAO implements BaseDAO<SalariedEmployee> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean update(SalariedEmployee salariedEmployee) {
        String sql = """
            UPDATE salariedEmployee SET
                name = ?,
                jobTitle = ?,
                yearlySalary = ?
            WHERE id = ?
        """;
        var numRows = jdbcTemplate.update(sql, salariedEmployee.getName(), salariedEmployee.getJobTitle(),
                salariedEmployee.getYearlySalary(), salariedEmployee.getId());
        return numRows == 1;
    }

    @Override
    public boolean delete(SalariedEmployee salariedEmployee) {
        String sql = """
            DELETE FROM salariedEmployee WHERE id = ?
        """;
        var numRows = jdbcTemplate.update(sql, salariedEmployee.getId());
        return numRows == 1;
    }

    @Override
    public SalariedEmployee insert(SalariedEmployee salariedEmployee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = """
            INSERT INTO salariedEmployee (name, jobTitle, yearlySalary)
                VALUES (?, ?, ?)
        """;
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, salariedEmployee.getName());
            ps.setString(2, salariedEmployee.getJobTitle());
            ps.setDouble(3, salariedEmployee.getYearlySalary());
            return ps;
        }, keyHolder);
        salariedEmployee.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return salariedEmployee;
    }

    @Override
    public Optional<SalariedEmployee> findById(Long id) {
        String sql = """
            SELECT id, name, jobTitle, yearlySalary
            FROM salariedEmployee WHERE id = ?
        """;
        var results = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SalariedEmployee.class), id);
        if (results.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(results.getFirst());
    }

    @Override
    public List<SalariedEmployee> findAll() {
        String sql = """
            SELECT id, name, jobTitle, yearlySalary
            FROM salariedEmployee
        """;
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(SalariedEmployee.class));
    }
}
