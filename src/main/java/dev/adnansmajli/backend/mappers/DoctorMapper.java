package dev.adnansmajli.backend.mappers;

import dev.adnansmajli.backend.dtos.DoctorDto;
import dev.adnansmajli.backend.models.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    /** Manual mapping so all fields get copied */
    default DoctorDto toDto(Doctor d) {
        if (d == null) return null;
        DoctorDto dto = new DoctorDto();
        dto.setId(d.getId());
        dto.setPersonalNo(d.getPersonalNo());
        dto.setFirstName(d.getFirstName());
        dto.setLastName(d.getLastName());
        dto.setSpecialization(d.getSpecialization());
        dto.setEmail(d.getEmail());
        dto.setPhoneNumber(d.getPhoneNumber());
        dto.setAddress(d.getAddress());
        // if you switched to String, use getGender(); if still char, convert:
        dto.setGender(d.getGender());
        dto.setBirthDate(d.getBirthDate());
        dto.setActive(d.isActive());
        dto.setPhoto(d.getPhoto());
        dto.setNotes(d.getNotes());
        dto.setCity(d.getCity());
        dto.setCountry(d.getCountry());
        dto.setPostalCode(d.getPostalCode());
        return dto;
    }

    /** Let MapStruct generate entity from DTO if you need it */
    Doctor toEntity(DoctorDto dto);
}
