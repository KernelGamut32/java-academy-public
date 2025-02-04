package ttl.larku.service;

import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.Course;

import java.util.List;

public class CourseService {

    private BaseDAO<Course> courseDAO;

    public CourseService() {

    }

    public CourseService(BaseDAO<Course> courseDAO) {
        this.courseDAO = courseDAO;
    }

    public Course createCourse(String code, String title) {
        Course course = new Course(code, title);
        course = courseDAO.insert(course);

        return course;
    }

    public Course createCourse(Course course) {
        course = courseDAO.insert(course);

        return course;
    }

    public boolean deleteCourse(int id) {
        Course course = courseDAO.findById(id);
        if (course != null) {
            return courseDAO.delete(course);
        }
        return false;
    }

    public boolean updateCourse(Course newCourse) {
        Course oldCourse = courseDAO.findById(newCourse.getId());
        if(oldCourse != null) {
            return courseDAO.update(newCourse);
        }
        return false;
    }

    public Course getCourseByCode(String code) {
        List<Course> courses = courseDAO.findBy(c -> c.getCode().equals(code));
        return !courses.isEmpty() ? courses.get(0) : null;
    }

    public Course getCourse(int id) {
        return courseDAO.findById(id);
    }

    public List<Course> getAllCourses() {
        return courseDAO.findAll();
    }

    public BaseDAO<Course> getCourseDAO() {
        return courseDAO;
    }

    public void setCourseDAO(BaseDAO<Course> courseDAO) {
        this.courseDAO = courseDAO;
    }

    public void clear() {
        courseDAO.deleteStore();
        courseDAO.createStore();
    }
}
