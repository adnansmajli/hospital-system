package dev.adnansmajli.backend.mappers;

import dev.adnansmajli.backend.dtos.DoctorDto;
import dev.adnansmajli.backend.models.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR   // MapStruct will warn if you add a new field and forget it
)
public interface DoctorMapper {


    default Doctor toEntity(DoctorDto dto) {
        if (dto == null) return null;

        Doctor d = new Doctor();
        d.setId(dto.getId());
        d.setPersonalNo(dto.getPersonalNo());
        d.setFirstName(dto.getFirstName());
        d.setLastName(dto.getLastName());
        d.setSpecialization(dto.getSpecialization());
        d.setEmail(dto.getEmail());
        d.setPhoneNumber(dto.getPhoneNumber());
        d.setAddress(dto.getAddress());
        d.setGender(dto.getGender());
        d.setBirthDate(dto.getBirthDate());
        d.setActive(dto.isActive());
        d.setPhoto(dto.getPhoto());
        d.setNotes(dto.getNotes());
        d.setCity(dto.getCity());
        d.setCountry(dto.getCountry());
        d.setPostalCode(dto.getPostalCode());
        return d;
    }


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
}

