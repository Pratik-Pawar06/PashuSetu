package com.pashusetu.pashusetu.controller;

import com.pashusetu.pashusetu.entity.BreedingRecord;
import com.pashusetu.pashusetu.service.BreedingRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breeding-records")
public class BreedingRecordController {

    private final BreedingRecordService breedingRecordService;

    public BreedingRecordController(
            BreedingRecordService breedingRecordService) {
        this.breedingRecordService = breedingRecordService;
    }

    @PostMapping
    public ResponseEntity<BreedingRecord> addBreedingRecord(
            @RequestBody BreedingRecord breedingRecord) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(breedingRecordService.addBreedingRecord(breedingRecord));
    }

    @GetMapping
    public ResponseEntity<List<BreedingRecord>> getAllBreedingRecords() {

        return ResponseEntity.ok(
                breedingRecordService.getAllBreedingRecords()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreedingRecord> getBreedingRecordById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                breedingRecordService.getBreedingRecordById(id)
        );
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<BreedingRecord>> getBreedingRecordsByAnimal(
            @PathVariable Long animalId) {

        return ResponseEntity.ok(
                breedingRecordService.getBreedingRecordsByAnimalId(animalId)
        );
    }

    @GetMapping("/veterinarian/{veterinarianId}")
    public ResponseEntity<List<BreedingRecord>> getBreedingRecordsByVeterinarian(
            @PathVariable Long veterinarianId) {

        return ResponseEntity.ok(
                breedingRecordService.getBreedingRecordsByVeterinarianId(
                        veterinarianId
                )
        );
    }

    @GetMapping("/pregnancy-checks/upcoming")
    public ResponseEntity<List<BreedingRecord>> getUpcomingPregnancyChecks() {

        return ResponseEntity.ok(
                breedingRecordService.getUpcomingPregnancyChecks()
        );
    }

    @GetMapping("/calving/upcoming")
    public ResponseEntity<List<BreedingRecord>> getUpcomingCalvingDates() {

        return ResponseEntity.ok(
                breedingRecordService.getUpcomingCalvingDates()
        );
    }

    @GetMapping("/pregnancy-checks/overdue")
    public ResponseEntity<List<BreedingRecord>> getOverduePregnancyChecks() {

        return ResponseEntity.ok(
                breedingRecordService.getOverduePregnancyChecks()
        );
    }

    @GetMapping("/calving/overdue")
    public ResponseEntity<List<BreedingRecord>> getOverdueCalvingDates() {

        return ResponseEntity.ok(
                breedingRecordService.getOverdueCalvingDates()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<BreedingRecord> updateBreedingRecord(
            @PathVariable Long id,
            @RequestBody BreedingRecord breedingRecord) {

        return ResponseEntity.ok(
                breedingRecordService.updateBreedingRecord(
                        id,
                        breedingRecord
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBreedingRecord(
            @PathVariable Long id) {

        breedingRecordService.deleteBreedingRecord(id);

        return ResponseEntity.noContent().build();
    }
}