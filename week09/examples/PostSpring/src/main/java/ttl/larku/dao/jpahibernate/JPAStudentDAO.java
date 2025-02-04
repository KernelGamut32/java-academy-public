package ttl.larku.dao.jpahibernate;

import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class JPAStudentDAO implements BaseDAO<Student> {

    private Map<Integer, Student> students = new ConcurrentHashMap<>();
    private AtomicInteger nextId = new AtomicInteger(1);
    private final String from;

    public JPAStudentDAO(String from) {
        this.from = from + ": ";
    }

    public JPAStudentDAO() {
        this("JPA");
    }

    public boolean update(Student updateObject) {
        return students.computeIfPresent(updateObject.getId(), (key, oldValue) -> updateObject) != null;
    }

    public boolean delete(Student student) {
        return students.remove(student.getId()) != null;
    }

    public Student insert(Student newObject) {
        int newId = nextId.getAndIncrement();
        newObject.setId(newId);

        newObject.setName(from + newObject.getName());
        students.put(newId, newObject);

        return newObject;
    }

    public Student findById(int id) {
        return students.get(id);
    }

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    public void deleteStore() {
        students = null;
    }

    public void createStore() {
        students = new ConcurrentHashMap<>();
        nextId = new AtomicInteger(1);
    }

}
