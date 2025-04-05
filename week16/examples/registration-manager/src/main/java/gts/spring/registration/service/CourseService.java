package gts.spring.registration.service;

import gts.spring.registration.dto.CourseDTO;
import gts.spring.registration.entity.Course;
import gts.spring.registration.mapper.CourseMapper;
import gts.spring.registration.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService extends CrudService<Course, CourseDTO, CourseRepository, CourseMapper>
{

    public CourseService(CourseRepository repository, CourseMapper mapper) {
        super(repository, mapper);
    }
}
