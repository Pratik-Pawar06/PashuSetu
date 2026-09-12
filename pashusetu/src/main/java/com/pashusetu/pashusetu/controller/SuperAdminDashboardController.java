package com.pashusetu.pashusetu.controller;

import com.pashusetu.pashusetu.dto.SuperAdminDashboardResponse;
import com.pashusetu.pashusetu.service.SuperAdminDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard/admin")
public class SuperAdminDashboardController {

    private final SuperAdminDashboardService dashboardService;

    public SuperAdminDashboardController(
            SuperAdminDashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<SuperAdminDashboardResponse> getDashboard() {

        return ResponseEntity.ok(
                dashboardService.getSuperAdminDashboard()
        );
    }
}