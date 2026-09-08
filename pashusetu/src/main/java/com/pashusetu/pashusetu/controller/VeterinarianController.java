package com.pashusetu.pashusetu.controller;

import com.pashusetu.pashusetu.entity.VeterinarianType;
import com.pashusetu.pashusetu.entity.Veterinarian;
import com.pashusetu.pashusetu.service.VeterinarianService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarians")
public class VeterinarianController {

    private final VeterinarianService veterinarianService;

    public VeterinarianController(
            VeterinarianService veterinarianService) {
        this.veterinarianService = veterinarianService;
    }

    @PostMapping
    public ResponseEntity<Veterinarian> addVeterinarian(
            @RequestBody Veterinarian veterinarian) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(veterinarianService.addVeterinarian(veterinarian));
    }

    @GetMapping
    public ResponseEntity<List<Veterinarian>> getAllVeterinarians() {

        return ResponseEntity.ok(
                veterinarianService.getAllVeterinarians()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veterinarian> getVeterinarianById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                veterinarianService.getVeterinarianById(id)
        );
    }

    @GetMapping("/license/{licenseNumber}")
    public ResponseEntity<Veterinarian> getVeterinarianByLicenseNumber(
            @PathVariable String licenseNumber) {

        return ResponseEntity.ok(
                veterinarianService
                        .getVeterinarianByLicenseNumber(licenseNumber)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Veterinarian> getVeterinarianByUserId(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                veterinarianService
                        .getVeterinarianByUserId(userId)
        );
    }

    @GetMapping("/district/{districtId}")
    public ResponseEntity<List<Veterinarian>> getVeterinariansByDistrict(
            @PathVariable Long districtId) {

        return ResponseEntity.ok(
                veterinarianService
                        .getVeterinariansByDistrictId(districtId)
        );
    }

    @GetMapping("/taluka/{talukaId}")
    public ResponseEntity<List<Veterinarian>> getVeterinariansByTaluka(
            @PathVariable Long talukaId) {

        return ResponseEntity.ok(
                veterinarianService
                        .getVeterinariansByTalukaId(talukaId)
        );
    }

    @GetMapping("/available")
    public ResponseEntity<List<Veterinarian>> getAvailableVeterinarians() {

        return ResponseEntity.ok(
                veterinarianService.getAvailableVeterinarians()
        );
    }

    @GetMapping("/approved")
    public ResponseEntity<List<Veterinarian>> getApprovedVeterinarians() {

        return ResponseEntity.ok(
                veterinarianService.getApprovedVeterinarians()
        );
    }


    @GetMapping("/taluka/{talukaId}/approved")
    public ResponseEntity<List<Veterinarian>>
    getApprovedVeterinariansByTaluka(
            @PathVariable Long talukaId) {

        return ResponseEntity.ok(
                veterinarianService
                        .getApprovedVeterinariansByTaluka(talukaId)
        );
    }


    @GetMapping("/taluka/{talukaId}/approved/type/{veterinarianType}")
    public ResponseEntity<List<Veterinarian>>
    getApprovedVeterinariansByTalukaAndType(
            @PathVariable Long talukaId,
            @PathVariable VeterinarianType veterinarianType) {

        return ResponseEntity.ok(
                veterinarianService
                        .getApprovedVeterinariansByTalukaAndType(
                                talukaId,
                                veterinarianType
                        )
        );
    }


    @GetMapping("/taluka/{talukaId}/approved/available")
    public ResponseEntity<List<Veterinarian>>
    getAvailableApprovedVeterinariansByTaluka(
            @PathVariable Long talukaId) {

        return ResponseEntity.ok(
                veterinarianService
                        .getAvailableApprovedVeterinariansByTaluka(
                                talukaId
                        )
        );
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Void> approveVeterinarian(
            @PathVariable Long id) {

        veterinarianService.approveVeterinarian(id);

        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}/reject")
    public ResponseEntity<Void> rejectVeterinarian(
            @PathVariable Long id) {

        veterinarianService.rejectVeterinarian(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veterinarian> updateVeterinarian(
            @PathVariable Long id,
            @RequestBody Veterinarian veterinarian) {

        return ResponseEntity.ok(
                veterinarianService
                        .updateVeterinarian(id, veterinarian)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeterinarian(
            @PathVariable Long id) {

        veterinarianService.deleteVeterinarian(id);

        return ResponseEntity.noContent().build();
    }
}