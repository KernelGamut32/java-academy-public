package gts.spring.registration.dao.inmemory;

import gts.spring.registration.dao.BaseDAO;
import gts.spring.registration.domain.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryCourseDAO implements BaseDAO<Course> {

    private Map<Long, Course> courses = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong(1);

    @Override
    public boolean update(Course course) {
        return courses.computeIfPresent(course.getId(),
                (key, oldValue) -> course) != null;
    }

    @Override
    public boolean delete(Course course) {
        return courses.remove(course.getId()) != null;
    }

    @Override
    public Course insert(Course course) {
        Long newId = nextId.getAndIncrement();
        course.setId(newId);
        courses.put(newId, course);

        return course;
    }

    @Override
    public Optional<Course> findById(Long id) {
        return Optional.ofNullable(courses.get(id));
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courses.values());
    }

    @Override
    public void resetDataStore() {
        courses = new ConcurrentHashMap<>();
        nextId = new AtomicLong(1);
    }
}
