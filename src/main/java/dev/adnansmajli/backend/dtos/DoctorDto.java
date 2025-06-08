    package dev.adnansmajli.backend.dtos;
    
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    
    import java.time.LocalDate;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class DoctorDto {
        private Long id;
        private String personalNo;
        private String firstName;
        private String lastName;
        private String specialization;
        private String email;
        private String phoneNumber;
        private String address;
        private String gender;
        private LocalDate birthDate;
        private boolean active;
        private String photo;
        private String notes;
        private String city;
        private String country;
        private String postalCode;
    }
    
