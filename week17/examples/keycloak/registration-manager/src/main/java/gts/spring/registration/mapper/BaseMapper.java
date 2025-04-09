package gts.spring.registration.mapper;

import gts.spring.registration.dto.BaseDTO;
import gts.spring.registration.entity.BaseEntity;
import org.mapstruct.MappingTarget;

public interface BaseMapper<T extends BaseEntity, U extends BaseDTO> {

    U toDTO(T entity);
    T toEntity(U dto);
    void updateEntityFromDTO(U dto, @MappingTarget T entity);
}
