package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.dto.DairyOfficerDashboardResponse;

public interface DairyOfficerDashboardService {

    DairyOfficerDashboardResponse getDairyOfficerDashboard(
            Long officerId
    );
}