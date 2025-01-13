package expeditors.week07.registration.dao;

import expeditors.week07.registration.domain.Course;

import java.util.List;

public interface CourseDAO {

    void create(Course course);
    Course get(int id);
    List<Course> getAll();
    void update(Course course);
    void delete(Course course);
    boolean contains(Course course);
    boolean contains(int id);

}
