package dev.adnansmajli.backend.service;


import dev.adnansmajli.backend.models.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAll();

    Doctor findById(long id);
    Doctor add(Doctor doctor);
    Doctor modify(Long id, Doctor doctor);
    void deleteById(long id);

    List<Doctor> findBySpecialization(String specialization);

}