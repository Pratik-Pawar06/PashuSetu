package com.pashusetu.pashusetu.dto;

public class DairyOfficerDashboardResponse {

    private Long officerId;
    private String officerName;

    private long totalFarmers;
    private long totalAnimals;
    private long totalVeterinarians;
    private long governmentVeterinarians;
    private long privateVeterinarians;
    private long approvedVeterinarians;
    private long pendingVeterinarianVerification;

    private long upcomingVaccinations;
    private long overdueVaccinations;
    private long activePregnancies;

    public DairyOfficerDashboardResponse() {
    }

    public DairyOfficerDashboardResponse(
            Long officerId,
            String officerName,
            long totalFarmers,
            long totalAnimals,
            long totalVeterinarians,
            long governmentVeterinarians,
            long privateVeterinarians,
            long approvedVeterinarians,
            long pendingVeterinarianVerification,
            long upcomingVaccinations,
            long overdueVaccinations,
            long activePregnancies) {

        this.officerId = officerId;
        this.officerName = officerName;
        this.totalFarmers = totalFarmers;
        this.totalAnimals = totalAnimals;
        this.totalVeterinarians = totalVeterinarians;
        this.governmentVeterinarians = governmentVeterinarians;
        this.privateVeterinarians = privateVeterinarians;
        this.approvedVeterinarians = approvedVeterinarians;
        this.pendingVeterinarianVerification = pendingVeterinarianVerification;
        this.upcomingVaccinations = upcomingVaccinations;
        this.overdueVaccinations = overdueVaccinations;
        this.activePregnancies = activePregnancies;
    }

    public Long getOfficerId() {
        return officerId;
    }

    public String getOfficerName() {
        return officerName;
    }

    public long getTotalFarmers() {
        return totalFarmers;
    }

    public long getTotalAnimals() {
        return totalAnimals;
    }

    public long getTotalVeterinarians() {
        return totalVeterinarians;
    }

    public long getGovernmentVeterinarians() {
        return governmentVeterinarians;
    }

    public long getPrivateVeterinarians() {
        return privateVeterinarians;
    }

    public long getApprovedVeterinarians() {
        return approvedVeterinarians;
    }

    public long getPendingVeterinarianVerification() {
        return pendingVeterinarianVerification;
    }

    public long getUpcomingVaccinations() {
        return upcomingVaccinations;
    }

    public long getOverdueVaccinations() {
        return overdueVaccinations;
    }

    public long getActivePregnancies() {
        return activePregnancies;
    }
}