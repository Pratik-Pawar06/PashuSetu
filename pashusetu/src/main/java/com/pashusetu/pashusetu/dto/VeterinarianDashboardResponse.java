package com.pashusetu.pashusetu.dto;

public class VeterinarianDashboardResponse {

    private Long veterinarianId;
    private String veterinarianName;

    private long todayAppointments;
    private long pendingAppointments;
    private long acceptedAppointments;
    private long completedAppointments;
    private long upcomingAppointments;
    private long pregnancyCheckups;
    private long followUps;

    private Boolean available;

    public VeterinarianDashboardResponse() {
    }

    public VeterinarianDashboardResponse(
            Long veterinarianId,
            String veterinarianName,
            long todayAppointments,
            long pendingAppointments,
            long acceptedAppointments,
            long completedAppointments,
            long upcomingAppointments,
            long pregnancyCheckups,
            long followUps,
            Boolean available) {

        this.veterinarianId = veterinarianId;
        this.veterinarianName = veterinarianName;
        this.todayAppointments = todayAppointments;
        this.pendingAppointments = pendingAppointments;
        this.acceptedAppointments = acceptedAppointments;
        this.completedAppointments = completedAppointments;
        this.upcomingAppointments = upcomingAppointments;
        this.pregnancyCheckups = pregnancyCheckups;
        this.followUps = followUps;
        this.available = available;
    }

    public Long getVeterinarianId() {
        return veterinarianId;
    }

    public String getVeterinarianName() {
        return veterinarianName;
    }

    public long getTodayAppointments() {
        return todayAppointments;
    }

    public long getPendingAppointments() {
        return pendingAppointments;
    }

    public long getAcceptedAppointments() {
        return acceptedAppointments;
    }

    public long getCompletedAppointments() {
        return completedAppointments;
    }

    public long getUpcomingAppointments() {
        return upcomingAppointments;
    }

    public long getPregnancyCheckups() {
        return pregnancyCheckups;
    }

    public long getFollowUps() {
        return followUps;
    }

    public Boolean getAvailable() {
        return available;
    }
}