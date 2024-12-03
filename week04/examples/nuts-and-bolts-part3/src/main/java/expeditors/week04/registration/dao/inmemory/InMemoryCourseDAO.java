package expeditors.week04.registration.dao.inmemory;

import expeditors.week04.registration.domain.Course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryCourseDAO {
    private final Map<Integer, Course> courses = new HashMap<>();
    private static final AtomicInteger nextId = new AtomicInteger(1);

    public void create(Course course) {
        int id = nextId.getAndIncrement();
        course.setId(id);
        courses.put(id, course);
    }

    public Course get(int id) {
        return courses.get(id);
    }

    public List<Course> getAll() {
        return new ArrayList<>(courses.values());
    }

    public void update(Course course) {
        if (courses.containsKey(course.getId())) {
            courses.put(course.getId(), course);
        }
    }

    public void delete(Course course) {
        courses.remove(course.getId());
    }
}
