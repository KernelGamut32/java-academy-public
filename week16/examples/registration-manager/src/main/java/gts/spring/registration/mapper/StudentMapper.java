package gts.spring.registration.mapper;

import gts.spring.registration.dto.StudentDTO;
import gts.spring.registration.entity.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StudentMapper extends BaseMapper<Student, StudentDTO> {

    @Override
    StudentDTO toDTO(Student entity);

    @Override
    @Mapping(target = "scheduledClasses", ignore = true)
    Student toEntity(StudentDTO dto);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "scheduledClasses", ignore = true)
    void updateEntityFromDTO(StudentDTO dto, @MappingTarget Student entity);
}
