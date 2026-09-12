package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.dto.VeterinarianDashboardResponse;

public interface VeterinarianDashboardService {

    VeterinarianDashboardResponse getVeterinarianDashboard(
            Long veterinarianId
    );
}