package dev.adnansmajli.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates IDs
    private Long id;

    @Column(unique = true, nullable = false)
    private String personalNo;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String specialization;

    @Column(nullable = false, unique = true)
    private String email;

    private String phoneNumber;

    private String address;

    @Column(nullable = false)
    private String gender;

    private LocalDate birthDate;

    @Column(nullable = false)
    private boolean active;

    private String photo;

    private String notes;

    private String city;

    private String country;

    private String postalCode;

    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Patient patient;

}