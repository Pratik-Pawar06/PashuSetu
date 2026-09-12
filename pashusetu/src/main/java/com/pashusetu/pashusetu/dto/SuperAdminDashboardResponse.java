package com.pashusetu.pashusetu.dto;

public class SuperAdminDashboardResponse {

    private long totalUsers;
    private long totalFarmers;
    private long totalVeterinarians;
    private long totalDairyOfficers;
    private long totalSuperAdmins;

    private long governmentVeterinarians;
    private long privateVeterinarians;

    private long approvedVeterinarians;
    private long pendingVeterinarianVerification;
    private long rejectedVeterinarians;

    public SuperAdminDashboardResponse() {
    }

    public SuperAdminDashboardResponse(
            long totalUsers,
            long totalFarmers,
            long totalVeterinarians,
            long totalDairyOfficers,
            long totalSuperAdmins,
            long governmentVeterinarians,
            long privateVeterinarians,
            long approvedVeterinarians,
            long pendingVeterinarianVerification,
            long rejectedVeterinarians) {

        this.totalUsers = totalUsers;
        this.totalFarmers = totalFarmers;
        this.totalVeterinarians = totalVeterinarians;
        this.totalDairyOfficers = totalDairyOfficers;
        this.totalSuperAdmins = totalSuperAdmins;
        this.governmentVeterinarians = governmentVeterinarians;
        this.privateVeterinarians = privateVeterinarians;
        this.approvedVeterinarians = approvedVeterinarians;
        this.pendingVeterinarianVerification = pendingVeterinarianVerification;
        this.rejectedVeterinarians = rejectedVeterinarians;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public long getTotalFarmers() {
        return totalFarmers;
    }

    public long getTotalVeterinarians() {
        return totalVeterinarians;
    }

    public long getTotalDairyOfficers() {
        return totalDairyOfficers;
    }

    public long getTotalSuperAdmins() {
        return totalSuperAdmins;
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

    public long getRejectedVeterinarians() {
        return rejectedVeterinarians;
    }
}