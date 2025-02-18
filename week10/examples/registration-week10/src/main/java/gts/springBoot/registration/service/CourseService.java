package gts.springBoot.registration.service;

import gts.springBoot.registration.dao.BaseDAO;
import gts.springBoot.registration.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final BaseDAO<Course> courseDAO;

    @Autowired
    public CourseService(BaseDAO<Course> courseDAO) {
        this.courseDAO = courseDAO;
    }

    public Course createCourse(Course course) {
        course = courseDAO.insert(course);

        return course;
    }

    public boolean deleteCourse(int id) {
        Course course = courseDAO.findById(id);
        if (course != null) {
            courseDAO.delete(course);
            return true;
        }
        return false;
    }

    public boolean updateCourse(Course newCourse) {
        Course oldCourse = courseDAO.findById(newCourse.getId());
        if(oldCourse != null) {
            courseDAO.update(newCourse);
            return true;
        }
        return false;
    }

    public List<Course> getCourseByCode(String code) {
        return courseDAO.findBy(c -> c.getCode().toLowerCase().contains(code.toLowerCase()));
    }

    public Course getCourse(int id) {
        return courseDAO.findById(id);
    }

    public List<Course> getAllCourses() {
        return courseDAO.findAll();
    }
}
