package com.pashusetu.pashusetu.controller;

import com.pashusetu.pashusetu.entity.MedicalRecord;
import com.pashusetu.pashusetu.service.MedicalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(
            MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }


    @PostMapping
    public ResponseEntity<MedicalRecord> addMedicalRecord(
            @RequestBody MedicalRecord medicalRecord) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(medicalRecordService
                        .addMedicalRecord(medicalRecord));
    }


    @GetMapping
    public ResponseEntity<List<MedicalRecord>>
    getAllMedicalRecords() {

        return ResponseEntity.ok(
                medicalRecordService.getAllMedicalRecords()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord>
    getMedicalRecordById(@PathVariable Long id) {

        return ResponseEntity.ok(
                medicalRecordService.getMedicalRecordById(id)
        );
    }


    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<MedicalRecord>>
    getMedicalRecordsByAnimal(
            @PathVariable Long animalId) {

        return ResponseEntity.ok(
                medicalRecordService
                        .getMedicalRecordsByAnimalId(animalId)
        );
    }


    @GetMapping("/veterinarian/{veterinarianId}")
    public ResponseEntity<List<MedicalRecord>>
    getMedicalRecordsByVeterinarian(
            @PathVariable Long veterinarianId) {

        return ResponseEntity.ok(
                medicalRecordService
                        .getMedicalRecordsByVeterinarianId(
                                veterinarianId
                        )
        );
    }


    @GetMapping("/follow-ups/upcoming")
    public ResponseEntity<List<MedicalRecord>>
    getUpcomingFollowUps() {

        return ResponseEntity.ok(
                medicalRecordService.getUpcomingFollowUps()
        );
    }


    @GetMapping("/follow-ups/overdue")
    public ResponseEntity<List<MedicalRecord>>
    getOverdueFollowUps() {

        return ResponseEntity.ok(
                medicalRecordService.getOverdueFollowUps()
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord>
    updateMedicalRecord(
            @PathVariable Long id,
            @RequestBody MedicalRecord medicalRecord) {

        return ResponseEntity.ok(
                medicalRecordService.updateMedicalRecord(
                        id,
                        medicalRecord
                )
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    deleteMedicalRecord(@PathVariable Long id) {

        medicalRecordService.deleteMedicalRecord(id);

        return ResponseEntity.noContent().build();
    }
}