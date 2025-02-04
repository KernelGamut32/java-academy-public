package ttl.larku.dao.inmemory;

import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryCourseDAO implements BaseDAO<Course> {

    private Map<Integer, Course> courses = new ConcurrentHashMap<>();
    private AtomicInteger nextId = new AtomicInteger(1);
    private final String from;

    public InMemoryCourseDAO() {
        this("InMem");
    }

    public InMemoryCourseDAO(String from) {
        this.from = from;
    }

    @Override
    public boolean update(Course updateObject) {
        return courses.computeIfPresent(updateObject.getId(), (key, oldValue) -> updateObject) != null;
    }

    @Override
    public boolean delete(Course course) {
        return courses.remove(course.getId()) != null;
    }

    @Override
    public Course insert(Course newObject) {
        int newId = nextId.getAndIncrement();
        newObject.setId(newId);
        courses.put(newId, newObject);

        newObject.setName(from + newObject.getName());
        return newObject;
    }

    @Override
    public Course findById(int id) {
        return courses.get(id);
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courses.values());
    }

    @Override
    public void deleteStore() {
        courses = null;
    }

    @Override
    public void createStore() {
        courses = new ConcurrentHashMap<>();
        nextId = new AtomicInteger(1);
    }

    public void setCourses(Map<Integer, Course> courses) {
        this.courses = courses;
    }
}
