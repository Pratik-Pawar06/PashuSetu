package com.pashusetu.pashusetu.controller;

import com.pashusetu.pashusetu.entity.Vaccination;
import com.pashusetu.pashusetu.service.VaccinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vaccinations")
public class VaccinationController {

    private final VaccinationService vaccinationService;

    public VaccinationController(VaccinationService vaccinationService) {
        this.vaccinationService = vaccinationService;
    }

    @PostMapping
    public ResponseEntity<Vaccination> addVaccination(
            @RequestBody Vaccination vaccination) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(vaccinationService.addVaccination(vaccination));
    }

    @GetMapping
    public ResponseEntity<List<Vaccination>> getAllVaccinations() {

        return ResponseEntity.ok(
                vaccinationService.getAllVaccinations()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaccination> getVaccinationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                vaccinationService.getVaccinationById(id)
        );
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<Vaccination>> getVaccinationsByAnimal(
            @PathVariable Long animalId) {

        return ResponseEntity.ok(
                vaccinationService
                        .getVaccinationsByAnimalId(animalId)
        );
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<Vaccination>> getUpcomingVaccinations() {

        return ResponseEntity.ok(
                vaccinationService.getUpcomingVaccinations()
        );
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Vaccination>> getOverdueVaccinations() {

        return ResponseEntity.ok(
                vaccinationService.getOverdueVaccinations()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaccination> updateVaccination(
            @PathVariable Long id,
            @RequestBody Vaccination vaccination) {

        return ResponseEntity.ok(
                vaccinationService.updateVaccination(id, vaccination)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaccination(
            @PathVariable Long id) {

        vaccinationService.deleteVaccination(id);

        return ResponseEntity.noContent().build();
    }
}