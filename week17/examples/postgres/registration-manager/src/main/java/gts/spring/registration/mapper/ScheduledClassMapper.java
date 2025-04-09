package gts.spring.registration.mapper;

import gts.spring.registration.dto.ScheduledClassDTO;
import gts.spring.registration.entity.ScheduledClass;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = { StudentMapper.class, CourseMapper.class })
public interface ScheduledClassMapper extends BaseMapper<ScheduledClass, ScheduledClassDTO> {

    @Override
    @Mapping(target = "course", source = "course")
    @Mapping(target = "students", source = "students")
    ScheduledClassDTO toDTO(ScheduledClass entity);

    @Override
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "students", ignore = true)
    ScheduledClass toEntity(ScheduledClassDTO dto);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "students", ignore = true)
    void updateEntityFromDTO(ScheduledClassDTO dto, @MappingTarget ScheduledClass entity);
}
