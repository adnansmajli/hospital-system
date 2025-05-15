package dev.adnansmajli.backend.service;



import dev.adnansmajli.backend.models.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> findAllAppointments();
    Appointment findAppointmentById(Long id);
    Appointment createAppointment(Appointment appointment);
    Appointment updateAppointment(Long id, Appointment appointment);
    void deleteAppointment(Long id);
}
