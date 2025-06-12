package dev.adnansmajli.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "appointments")
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    private String firstName;
    private String lastName;

    @ManyToOne
    private Doctor doctor;

    private LocalDateTime appointmentDateTime;

    private LocalDate date;
    private LocalTime time;
    private String status; // "Scheduled", "Completed", "Cancelled"
    private String notes;
}
