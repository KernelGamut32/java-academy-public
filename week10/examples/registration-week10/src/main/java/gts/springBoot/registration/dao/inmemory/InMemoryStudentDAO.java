package gts.springBoot.registration.dao.inmemory;

import gts.springBoot.registration.dao.BaseDAO;
import gts.springBoot.registration.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryStudentDAO implements BaseDAO<Student> {

    private Map<Integer, Student> students = new ConcurrentHashMap<>();
    private AtomicInteger nextId = new AtomicInteger(1);

    @Override
    public boolean update(Student updateObject) {
        return students.computeIfPresent(updateObject.getId(),
                (key, oldValue) -> updateObject) != null;
    }

    @Override
    public boolean delete(Student student) {
        return students.remove(student.getId()) != null;
    }

    @Override
    public Student insert(Student newObject) {
        int newId = nextId.getAndIncrement();
        newObject.setId(newId);
        students.put(newId, newObject);

        return newObject;
    }

    @Override
    public Student findById(int id) {
        return students.get(id);
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    @Override
    public void resetDataStore() {
        students = new ConcurrentHashMap<>();
        nextId = new AtomicInteger(1);
    }
}
