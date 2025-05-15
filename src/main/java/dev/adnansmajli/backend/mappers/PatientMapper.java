package dev.adnansmajli.backend.mappers;

import dev.adnansmajli.backend.dtos.PatientDto;
import dev.adnansmajli.backend.infrastructure.mapping.SimpleMapper;
import dev.adnansmajli.backend.models.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper extends SimpleMapper<Patient, PatientDto> {
}
