package com.pashusetu.pashusetu.controller;

import com.pashusetu.pashusetu.dto.DairyAnimalUpdateRequest;
import com.pashusetu.pashusetu.dto.DairyAnimalRequest;
import com.pashusetu.pashusetu.entity.DairyAnimal;
import com.pashusetu.pashusetu.service.DairyAnimalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class DairyAnimalController {

    private final DairyAnimalService dairyAnimalService;

    public DairyAnimalController(DairyAnimalService dairyAnimalService) {
        this.dairyAnimalService = dairyAnimalService;
    }

    @PostMapping
    public ResponseEntity<DairyAnimal> registerAnimal(
            @Valid @RequestBody DairyAnimalRequest request) {

        DairyAnimal savedAnimal =
                dairyAnimalService.registerAnimal(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedAnimal);
    }
    
    @GetMapping
    public ResponseEntity<List<DairyAnimal>> getAllAnimals() {

        return ResponseEntity.ok(
                dairyAnimalService.getAllAnimals()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DairyAnimal> getAnimalById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                dairyAnimalService.getAnimalById(id)
        );
    }

    @GetMapping("/tag/{tagNumber}")
    public ResponseEntity<DairyAnimal> getAnimalByTagNumber(
            @PathVariable String tagNumber) {

        return ResponseEntity.ok(
                dairyAnimalService
                        .getAnimalByTagNumber(tagNumber)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DairyAnimal> updateAnimal(
            @PathVariable Long id,
            @Valid @RequestBody DairyAnimalUpdateRequest request) {

        return ResponseEntity.ok(
                dairyAnimalService.updateAnimal(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(
            @PathVariable Long id) {

        dairyAnimalService.deleteAnimal(id);

        return ResponseEntity.noContent().build();
    }
}