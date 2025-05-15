package dev.adnansmajli.backend.service;





import dev.adnansmajli.backend.models.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> findAll();

    Patient findById(long id);

    Patient add(Patient patient);

    Patient modify(Long id, Patient patient);

    void deleteById(long id);

    Patient findByFirstNameAndLastName(String firstName, String lastName);
}