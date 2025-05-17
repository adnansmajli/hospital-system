package dev.adnansmajli.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
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
    private Long doctorId; // optional relation
}
