package gts.spring.registration.mapper;

import gts.spring.registration.dto.CondensedClassDTO;
import gts.spring.registration.entity.ScheduledClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CondensedClassMapper {

    @Mapping(source = "course.code", target = "courseCode")
    CondensedClassDTO toDTO(ScheduledClass scheduledClass);
}
