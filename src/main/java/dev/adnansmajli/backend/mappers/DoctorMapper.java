package dev.adnansmajli.backend.mappers;

import dev.adnansmajli.backend.dtos.DoctorDto;
import dev.adnansmajli.backend.infrastructure.mapping.SimpleMapper;
import dev.adnansmajli.backend.models.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper extends SimpleMapper<Doctor, DoctorDto> {
}
