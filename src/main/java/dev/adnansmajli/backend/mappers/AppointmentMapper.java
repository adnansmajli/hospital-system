package dev.adnansmajli.backend.mappers;

import dev.adnansmajli.backend.dtos.AppointmentDto;
import dev.adnansmajli.backend.models.Appointment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    /** Manually map Appointment → AppointmentDto */
    default AppointmentDto toDto(Appointment appt) {
        if (appt == null) {
            return null;
        }
        AppointmentDto dto = new AppointmentDto();
        dto.setId(appt.getId());

        if (appt.getPatient() != null) {
            dto.setPatientId(appt.getPatient().getId());
            dto.setPatientName(
                    appt.getPatient().getFirstName() + " " +
                            appt.getPatient().getLastName()
            );
        }

        if (appt.getDoctor() != null) {
            dto.setDoctorId(appt.getDoctor().getId());
            dto.setDoctorName(
                    appt.getDoctor().getFirstName() + " " +
                            appt.getDoctor().getLastName()
            );
        }

        dto.setDate(appt.getDate());
        dto.setTime(appt.getTime());
        dto.setStatus(appt.getStatus());
        dto.setNotes(appt.getNotes());
        return dto;
    }

    /**
     * If you also need the reverse mapping (DTO → entity),
     * you can either write another default method here, or
     * leave MapStruct to generate it automatically by defining
     * Appointment fromDto(AppointmentDto dto);
     */
}
