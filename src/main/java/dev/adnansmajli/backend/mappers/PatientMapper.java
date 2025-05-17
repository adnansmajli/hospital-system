package dev.adnansmajli.backend.mappers;

import dev.adnansmajli.backend.dtos.PatientDto;
import dev.adnansmajli.backend.models.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    default PatientDto toDto(Patient p) {
        if (p == null) return null;
        PatientDto dto = new PatientDto();
        dto.setId(p.getId());
        dto.setPersonalNo(p.getPersonalNo());
        dto.setFirstName(p.getFirstName());
        dto.setLastName(p.getLastName());
        dto.setGender(p.getGender());
        dto.setBirthDate(p.getBirthDate());
        dto.setActive(p.isActive());
        dto.setEmail(p.getEmail());
        dto.setPhoneNumber(p.getPhoneNumber());
        dto.setAddress(p.getAddress());
        dto.setCity(p.getCity());
        dto.setCountry(p.getCountry());
        dto.setNationality(p.getNationality());
        dto.setPhoto(p.getPhoto());
        dto.setNotes(p.getNotes());
        return dto;
    }

    Patient toEntity(PatientDto dto);
}
