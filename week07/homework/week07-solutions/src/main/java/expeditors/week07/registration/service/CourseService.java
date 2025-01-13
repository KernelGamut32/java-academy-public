package expeditors.week07.registration.service;

import expeditors.week07.registration.dao.CourseDAO;
import expeditors.week07.registration.domain.Course;

import java.util.List;
import java.util.Objects;

public class CourseService {

    public static final String INVALID_INPUT_MESSAGE = "Invalid input provided for widget operation";

    private final CourseDAO courseDAO;

    public CourseService(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void createCourse(String title, String code, String description) throws IllegalArgumentException {
        if (title == null || title.isBlank() || code == null || code.isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
        Course course = new Course(title, code, description);
        courseDAO.create(course);
    }

    public void createCourse(Course course) throws IllegalArgumentException {
        if (course == null) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }
        createCourse(course.getTitle(), course.getCode(), course.getDescription());
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
        return courseDAO.contains(id) ? courseDAO.get(id) : null;
    }

    public List<Course> getAllCourses() {
        return courseDAO.getAll();
    }

    public void updateCourse(Course course) {
        if (course == null || course.getTitle() == null || course.getTitle().isBlank() ||
        course.getCode() == null || course.getCode().isBlank()) {
            throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
        }

        if (courseDAO.contains(course)) {
            courseDAO.update(course);
        }
    }

    public void deleteCourse(int id) {
        Course course = getCourse(id);
        if (course != null) {
            courseDAO.delete(course);
        }
    }

}
