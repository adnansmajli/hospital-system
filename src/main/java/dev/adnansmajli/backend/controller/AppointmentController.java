package dev.adnansmajli.backend.controller;

import dev.adnansmajli.backend.dtos.AppointmentDto;
import dev.adnansmajli.backend.mappers.AppointmentMapper;
import dev.adnansmajli.backend.models.Appointment;
import dev.adnansmajli.backend.models.Doctor;
import dev.adnansmajli.backend.models.Patient;
import dev.adnansmajli.backend.service.AppointmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentMapper appointmentMapper;

    public AppointmentController(AppointmentService appointmentService,
                                 AppointmentMapper appointmentMapper) {
        this.appointmentService = appointmentService;
        this.appointmentMapper = appointmentMapper;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> getAll() {
        List<AppointmentDto> dtos = appointmentService.findAllAppointments().stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getOne(@PathVariable Long id) {
        try {
            Appointment appointment = appointmentService.findAppointmentById(id);
            AppointmentDto dto = appointmentMapper.toDto(appointment);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AppointmentDto> create(@RequestBody AppointmentDto dto) {
        Appointment toSave = fromDto(dto);
        Appointment saved = appointmentService.createAppointment(toSave);
        AppointmentDto responseDto = appointmentMapper.toDto(saved);
        return ResponseEntity.created(URI.create("/api/appointments/" + saved.getId()))
                .body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> update(@PathVariable Long id,
                                                 @RequestBody AppointmentDto dto) {
        try {
            Appointment toUpdate = fromDto(dto);
            Appointment updated = appointmentService.updateAppointment(id, toUpdate);
            return ResponseEntity.ok(appointmentMapper.toDto(updated));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    private Appointment fromDto(AppointmentDto dto) {
        Appointment a = new Appointment();
        // link existing Patient and Doctor by id
        Patient p = new Patient();
        p.setId(dto.getPatientId());
        a.setPatient(p);
        Doctor d = new Doctor();
        d.setId(dto.getDoctorId());
        a.setDoctor(d);
        // map other fields
        a.setDate(dto.getDate());
        a.setTime(dto.getTime());
        a.setStatus(dto.getStatus());
        a.setNotes(dto.getNotes());
        return a;
    }
}
