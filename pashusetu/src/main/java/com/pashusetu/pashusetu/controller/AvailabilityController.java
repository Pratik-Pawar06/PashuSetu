package com.pashusetu.pashusetu.controller;

import com.pashusetu.pashusetu.entity.Availability;
import com.pashusetu.pashusetu.service.AvailabilityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Availability createAvailability(
            @RequestBody Availability availability) {
        return availabilityService.createAvailability(availability);
    }

    @GetMapping
    public List<Availability> getAllAvailability() {
        return availabilityService.getAllAvailability();
    }

    @GetMapping("/{id}")
    public Availability getAvailabilityById(@PathVariable Long id) {
        return availabilityService.getAvailabilityById(id);
    }

    @GetMapping("/veterinarian/{veterinarianId}")
    public List<Availability> getAvailabilityByVeterinarian(
            @PathVariable Long veterinarianId) {
        return availabilityService
                .getAvailabilityByVeterinarian(veterinarianId);
    }

    @PutMapping("/{id}")
    public Availability updateAvailability(
            @PathVariable Long id,
            @RequestBody Availability availability) {
        return availabilityService.updateAvailability(id, availability);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAvailability(@PathVariable Long id) {
        availabilityService.deleteAvailability(id);
    }
}