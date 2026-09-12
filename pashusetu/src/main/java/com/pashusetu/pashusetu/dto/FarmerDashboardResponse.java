package com.pashusetu.pashusetu.dto;

public class FarmerDashboardResponse {

    private Long farmerId;
    private String farmerName;

    private long totalAnimals;
    private long upcomingVaccinations;
    private long overdueVaccinations;
    private long upcomingAppointments;
    private long activePregnancies;
    private long unreadNotifications;

    public FarmerDashboardResponse() {
    }

    public FarmerDashboardResponse(
            Long farmerId,
            String farmerName,
            long totalAnimals,
            long upcomingVaccinations,
            long overdueVaccinations,
            long upcomingAppointments,
            long activePregnancies,
            long unreadNotifications) {

        this.farmerId = farmerId;
        this.farmerName = farmerName;
        this.totalAnimals = totalAnimals;
        this.upcomingVaccinations = upcomingVaccinations;
        this.overdueVaccinations = overdueVaccinations;
        this.upcomingAppointments = upcomingAppointments;
        this.activePregnancies = activePregnancies;
        this.unreadNotifications = unreadNotifications;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public long getTotalAnimals() {
        return totalAnimals;
    }

    public long getUpcomingVaccinations() {
        return upcomingVaccinations;
    }

    public long getOverdueVaccinations() {
        return overdueVaccinations;
    }

    public long getUpcomingAppointments() {
        return upcomingAppointments;
    }

    public long getActivePregnancies() {
        return activePregnancies;
    }

    public long getUnreadNotifications() {
        return unreadNotifications;
    }
}