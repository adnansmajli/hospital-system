package dev.adnansmajli.backend.controller;

import dev.adnansmajli.backend.dtos.DoctorDto;
import dev.adnansmajli.backend.models.Doctor;
import dev.adnansmajli.backend.mappers.DoctorMapper;
import dev.adnansmajli.backend.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    public DoctorController(DoctorService doctorService, DoctorMapper doctorMapper) {
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
    }

    @GetMapping
    public ResponseEntity<List<DoctorDto>> getAll() {
        List<DoctorDto> list = doctorService.findAll().stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> getOne(@PathVariable Long id) {
        try {
            Doctor d = doctorService.findById(id);
            return ResponseEntity.ok(doctorMapper.toDto(d));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DoctorDto> create(@RequestBody DoctorDto dto) {
        Doctor created = doctorService.add(doctorMapper.toEntity(dto));
        return ResponseEntity.created(URI.create("/api/doctors/" + created.getId()))
                .body(doctorMapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> update(@PathVariable Long id,
                                            @RequestBody DoctorDto dto) {
        try {
            Doctor updated = doctorService.modify(id, doctorMapper.toEntity(dto));
            return ResponseEntity.ok(doctorMapper.toDto(updated));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            doctorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
