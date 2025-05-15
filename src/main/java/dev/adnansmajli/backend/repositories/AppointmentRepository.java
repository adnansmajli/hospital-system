package dev.adnansmajli.backend.repositories;


import dev.adnansmajli.backend.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);
    Optional<Appointment> findByIdAndPatientId(Long id, Long patientId);
    List<Appointment> findByStatus(String status); // Optional status filtering
}
