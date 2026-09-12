package com.pashusetu.pashusetu.controller;

import com.pashusetu.pashusetu.dto.DairyOfficerDashboardResponse;
import com.pashusetu.pashusetu.service.DairyOfficerDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard/dairy-officer")
public class DairyOfficerDashboardController {

    private final DairyOfficerDashboardService dashboardService;

    public DairyOfficerDashboardController(
            DairyOfficerDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/{officerId}")
    public ResponseEntity<DairyOfficerDashboardResponse> getDashboard(
            @PathVariable Long officerId) {

        return ResponseEntity.ok(
                dashboardService.getDairyOfficerDashboard(officerId)
        );
    }
}