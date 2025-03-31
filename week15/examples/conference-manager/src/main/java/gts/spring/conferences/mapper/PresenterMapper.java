package gts.spring.conferences.mapper;

import gts.spring.conferences.dto.PresenterDTO;
import gts.spring.conferences.entity.Presenter;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PresenterMapper {

    PresenterDTO toDTO(Presenter entity);

    @Mapping(target = "conferenceSessions", ignore = true)
    Presenter toEntity(PresenterDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "conferenceSessions", ignore = true)
    void updateEntityFromDTO(PresenterDTO dto, @MappingTarget Presenter entity);
}
