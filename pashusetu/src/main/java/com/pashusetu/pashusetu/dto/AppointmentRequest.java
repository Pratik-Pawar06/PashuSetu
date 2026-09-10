package com.pashusetu.pashusetu.dto;

import com.pashusetu.pashusetu.entity.AppointmentReason;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentRequest {

    private Long farmerId;
    private Long veterinarianId;
    private Long animalId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private AppointmentReason reason;
    private String notes;

    public AppointmentRequest() {
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public Long getVeterinarianId() {
        return veterinarianId;
    }

    public void setVeterinarianId(Long veterinarianId) {
        this.veterinarianId = veterinarianId;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public AppointmentReason getReason() {
        return reason;
    }

    public void setReason(AppointmentReason reason) {
        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}