package gts.spring.registration.dao.inmemory;

import gts.spring.registration.dao.BaseDAO;
import gts.spring.registration.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryStudentDAO implements BaseDAO<Student> {

    private Map<Long, Student> students = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong(1);

    @Override
    public boolean update(Student student) {
        return students.computeIfPresent(student.getId(),
                (key, oldValue) -> student) != null;
    }

    @Override
    public boolean delete(Student student) {
        return students.remove(student.getId()) != null;
    }

    @Override
    public Student insert(Student student) {
        Long newId = nextId.getAndIncrement();
        student.setId(newId);
        students.put(newId, student);

        return student;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.ofNullable(students.get(id));
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    @Override
    public void resetDataStore() {
        students = new ConcurrentHashMap<>();
        nextId = new AtomicLong(1);
    }
}
