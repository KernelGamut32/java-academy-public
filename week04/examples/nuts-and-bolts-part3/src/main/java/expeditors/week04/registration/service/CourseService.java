package expeditors.week04.registration.service;

import expeditors.week04.registration.dao.inmemory.InMemoryCourseDAO;
import expeditors.week04.registration.domain.Course;

import java.util.List;
import java.util.Objects;

public class CourseService {
    private final InMemoryCourseDAO courseDAO;

    public CourseService() {
        courseDAO = new InMemoryCourseDAO();
    }

    public void createCourse(String title, String code, String description) {
        Course course = new Course(title, code, description);
        courseDAO.create(course);
    }

    public void createCourse(Course course) {
        courseDAO.create(course);
    }

    public Course getCourseByCode(String code) {
        List<Course> courses = courseDAO.getAll();
        for (Course course : courses) {
            if (Objects.equals(course.getCode(), code)) {
                return course;
            }
        }
        return null;
    }

    public Course getCourse(int id) {
        return courseDAO.get(id);
    }

    public List<Course> getAllCourses() {
        return courseDAO.getAll();
    }

    public void updateCourse(Course course) {
        courseDAO.update(course);
    }

    public void deleteCourse(int id) {
        Course course = courseDAO.get(id);
        if (course != null) {
            courseDAO.delete(course);
        }
    }
}
