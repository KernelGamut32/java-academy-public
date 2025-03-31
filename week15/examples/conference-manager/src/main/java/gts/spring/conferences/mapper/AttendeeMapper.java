package gts.spring.conferences.mapper;

import gts.spring.conferences.dto.AttendeeDTO;
import gts.spring.conferences.entity.Attendee;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AttendeeMapper {

    AttendeeDTO toDTO(Attendee entity);

    @Mapping(target = "conferenceSessions", ignore = true)
    Attendee toEntity(AttendeeDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "conferenceSessions", ignore = true)
    void updateEntityFromDTO(AttendeeDTO dto, @MappingTarget Attendee entity);
}
