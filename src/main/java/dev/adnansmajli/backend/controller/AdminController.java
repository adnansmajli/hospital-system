package dev.adnansmajli.backend.controller;

import dev.adnansmajli.backend.dtos.AppointmentDto;
import dev.adnansmajli.backend.dtos.DoctorDto;
import dev.adnansmajli.backend.dtos.UserDto;
import dev.adnansmajli.backend.mappers.AppointmentMapper;
import dev.adnansmajli.backend.mappers.DoctorMapper;
import dev.adnansmajli.backend.mappers.UserMapper;
import dev.adnansmajli.backend.service.AdminService;
import dev.adnansmajli.backend.service.AppointmentService;
import dev.adnansmajli.backend.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final UserMapper userMapper;
    private final DoctorMapper doctorMapper;
    private final AppointmentMapper appointmentMapper;

    // Logins  – renditur sipas orës së fundit
    @GetMapping("/logins")
    public List<UserDto> getLogins() {
        return adminService.getAllUsers().stream()
                .sorted(Comparator.comparing(UserDto::getLastLoginAt,
                        Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    @GetMapping("/signups")
    public ResponseEntity<List<UserDto>> getSignups() {
        Comparator<UserDto> byCreatedAtDesc =
                Comparator.comparing(UserDto::getCreatedAt,
                        Comparator.nullsLast(Comparator.reverseOrder()));

        List<UserDto> signups = adminService.getAllUsers()      // already returns all users
                .stream()
                .sorted(byCreatedAtDesc)
                .collect(Collectors.toList());

        return ResponseEntity.ok(signups);
    }
    // Appointments
    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        return ResponseEntity.ok(
                appointmentService.findAllAppointments().stream()
                        .map(appointmentMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    // Cancellations (filter by status)
    @GetMapping("/cancellations")
    public ResponseEntity<List<AppointmentDto>> getCancellations() {
        return ResponseEntity.ok(
                appointmentService.findAllAppointments().stream()
                        .filter(a -> "CANCELLED".equalsIgnoreCase(a.getStatus()))
                        .map(appointmentMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    // Doctors
    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        return ResponseEntity.ok(
                doctorService.findAll().stream()
                        .map(doctorMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/doctors")
    public ResponseEntity<DoctorDto> addDoctor(@RequestBody DoctorDto doctorDto) {
        var created = doctorService.add(doctorMapper.toEntity(doctorDto));
        return ResponseEntity.ok(doctorMapper.toDto(created));
    }
}