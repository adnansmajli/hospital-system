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
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstName;
    private String lastName;
    private String personalNo;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String notes;
    private String gender;
    private LocalDate birthDate;
    private boolean active;
    private String photo;
    private String nationality;


    @OneToOne()
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;




}