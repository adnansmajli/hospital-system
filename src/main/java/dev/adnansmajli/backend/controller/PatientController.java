package dev.adnansmajli.backend.controller;

import dev.adnansmajli.backend.dtos.PatientDto;
import dev.adnansmajli.backend.models.Patient;
import dev.adnansmajli.backend.mappers.PatientMapper;
import dev.adnansmajli.backend.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityNotFoundException;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;
    private final PatientMapper patientMapper;

    public PatientController(PatientService patientService, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAll() {
        List<PatientDto> list = patientService.findAll().stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getOne(@PathVariable Long id) {
        try {
            Patient p = patientService.findById(id);
            return ResponseEntity.ok(patientMapper.toDto(p));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PatientDto> create(@RequestBody PatientDto dto) {
        Patient created = patientService.add(patientMapper.toEntity(dto));
        return ResponseEntity.created(URI.create("/api/patients/" + created.getId()))
                .body(patientMapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> update(@PathVariable Long id,
                                             @RequestBody PatientDto dto) {
        try {
            Patient updated = patientService.modify(id, patientMapper.toEntity(dto));
            return ResponseEntity.ok(patientMapper.toDto(updated));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            patientService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
