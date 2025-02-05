package ttl.larku.jconfig;

import org.springframework.context.annotation.*;
import ttl.larku.dao.BaseDAO;
import ttl.larku.dao.inmemory.InMemoryCourseDAO;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.dao.jpahibernate.JPACourseDAO;
import ttl.larku.dao.jpahibernate.JPAStudentDAO;
import ttl.larku.domain.Course;
import ttl.larku.domain.Student;
import ttl.larku.service.CourseService;
import ttl.larku.service.StudentService;

@Configuration
@ComponentScan({"ttl.larku.service", "ttl.larku.dao"})
public class LarkUConfig {
    @Bean
    @Profile("development")
    public BaseDAO<Student> studentDAO() {
        return new InMemoryStudentDAO();
    }

    @Bean(name = "studentDAO")
    @Profile("production")
    public BaseDAO<Student> studentDAOJpa() {
        return new JPAStudentDAO();
    }

    @Bean
    @Profile("development")
    public BaseDAO<Course> courseDAO() {
        return new InMemoryCourseDAO();
    }

    @Bean(name = "courseDAO")
    @Profile("production")
    public BaseDAO<Course> courseDAOJpa() {
        return new JPACourseDAO();
    }

    @Bean
    public StudentService studentService() {
        StudentService ss = new StudentService();
        ss.setStudentDAO(studentDAO());
        return ss;
    }

    @Bean
    public CourseService courseService(BaseDAO<Course> courseDAO) {
        return new CourseService(courseDAO);
    }
}