package com.pashusetu.pashusetu.controller;

import com.pashusetu.pashusetu.dto.AppointmentRequest;
import com.pashusetu.pashusetu.entity.Appointment;
import com.pashusetu.pashusetu.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment createAppointment(@RequestBody AppointmentRequest request) {

        System.out.println(">>> APPOINTMENT POST REACHED CONTROLLER");

        return appointmentService.createAppointment(request);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/farmer/{farmerId}")
    public List<Appointment> getAppointmentsByFarmer(
            @PathVariable Long farmerId) {

        return appointmentService.getAppointmentsByFarmerId(farmerId);
    }

    @GetMapping("/veterinarian/{veterinarianId}")
    public List<Appointment> getAppointmentsByVeterinarian(
            @PathVariable Long veterinarianId) {

        return appointmentService.getAppointmentsByVeterinarianId(veterinarianId);
    }

    @PutMapping("/{id}/accept")
    public Appointment acceptAppointment(@PathVariable Long id) {
        return appointmentService.acceptAppointment(id);
    }

    @PutMapping("/{id}/reject")
    public Appointment rejectAppointment(@PathVariable Long id) {
        return appointmentService.rejectAppointment(id);
    }

    @PutMapping("/{id}/complete")
    public Appointment completeAppointment(@PathVariable Long id) {
        return appointmentService.completeAppointment(id);
    }

    @PutMapping("/{id}/cancel")
    public Appointment cancelAppointment(@PathVariable Long id) {
        return appointmentService.cancelAppointment(id);
    }
}