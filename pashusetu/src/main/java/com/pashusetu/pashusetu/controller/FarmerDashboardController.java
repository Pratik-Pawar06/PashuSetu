package com.pashusetu.pashusetu.controller;

import com.pashusetu.pashusetu.dto.FarmerDashboardResponse;
import com.pashusetu.pashusetu.service.FarmerDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard/farmer")
public class FarmerDashboardController {

    private final FarmerDashboardService farmerDashboardService;

    public FarmerDashboardController(
            FarmerDashboardService farmerDashboardService) {

        this.farmerDashboardService = farmerDashboardService;
    }

    @GetMapping("/{farmerId}")
    public ResponseEntity<FarmerDashboardResponse> getFarmerDashboard(
            @PathVariable Long farmerId) {

        return ResponseEntity.ok(
                farmerDashboardService.getFarmerDashboard(farmerId)
        );
    }
}