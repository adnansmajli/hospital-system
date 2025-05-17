package dev.adnansmajli.backend.infrastructure.mapping;

import dev.adnansmajli.backend.dtos.PatientDto;

public interface SimpleMapper<TEntity, TDto> {
    TEntity toEntity(TDto dto);

    TDto toDto(TEntity entity);
}
