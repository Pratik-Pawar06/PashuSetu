package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.dto.FarmerDashboardResponse;

public interface FarmerDashboardService {

    FarmerDashboardResponse getFarmerDashboard(Long farmerId);
}