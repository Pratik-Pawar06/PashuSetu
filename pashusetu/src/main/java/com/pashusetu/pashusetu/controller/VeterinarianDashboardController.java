package com.pashusetu.pashusetu.controller;

import com.pashusetu.pashusetu.dto.VeterinarianDashboardResponse;
import com.pashusetu.pashusetu.service.VeterinarianDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard/veterinarian")
public class VeterinarianDashboardController {

    private final VeterinarianDashboardService dashboardService;

    public VeterinarianDashboardController(
            VeterinarianDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/{veterinarianId}")
    public ResponseEntity<VeterinarianDashboardResponse> getDashboard(
            @PathVariable Long veterinarianId) {

        return ResponseEntity.ok(
                dashboardService.getVeterinarianDashboard(veterinarianId)
        );
    }
}