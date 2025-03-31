package gts.spring.conferences.mapper;

import gts.spring.conferences.dto.ConferenceSessionDTO;
import gts.spring.conferences.entity.ConferenceSession;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = { PresenterMapper.class, AttendeeMapper.class })
public interface ConferenceSessionMapper {

    @Mapping(target = "presenters", source = "presenters")
    @Mapping(target = "attendees", source = "attendees")
    ConferenceSessionDTO toDTO(ConferenceSession entity);

    @Mapping(target = "presenters", ignore = true)
    @Mapping(target = "attendees", ignore = true)
    ConferenceSession toEntity(ConferenceSessionDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "presenters", ignore = true)
    @Mapping(target = "attendees", ignore = true)
    void updateEntityFromDTO(ConferenceSessionDTO dto, @MappingTarget ConferenceSession entity);
}
