package gts.spring.registration.mapper;

import gts.spring.registration.dto.CourseDTO;
import gts.spring.registration.entity.Course;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CourseMapper extends BaseMapper<Course, CourseDTO> {

    @Override
    CourseDTO toDTO(Course entity);

    @Override
    @Mapping(target = "scheduledClasses", ignore = true)
    Course toEntity(CourseDTO dto);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "scheduledClasses", ignore = true)
    void updateEntityFromDTO(CourseDTO dto, @MappingTarget Course entity);
}
