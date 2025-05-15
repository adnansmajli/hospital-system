package dev.adnansmajli.backend.repositories;



import dev.adnansmajli.backend.models.Doctor;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByPersonalNo(String personalNo);
    List<Doctor> findAllByCityAndGender(String city, char gender);

    List<Doctor> findAllByFirstNameContaining(String firstName);

    List<Doctor> findAllByBirthDateBetweenOrderByBirthDate(LocalDate from, LocalDate to);

    List<Doctor> findAllByActive(boolean active, Limit limit);

    List<Doctor> findAllByFirstNameOrLastNameOrCityOrBirthDate(String firstName, String lastName, String city, LocalDate birthDate);



    List<Doctor> findAllByEmailEndingWithAndPhoneNumberStartingWith(String email, String phoneNumber);



    Long countAllByPersonalNoOrEmail(String personalNo, String email);


}