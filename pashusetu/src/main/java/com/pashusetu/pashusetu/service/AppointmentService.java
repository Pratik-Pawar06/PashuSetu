package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.dto.AppointmentRequest;
import com.pashusetu.pashusetu.entity.Appointment;

import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(AppointmentRequest request);

    List<Appointment> getAllAppointments();

    Appointment getAppointmentById(Long id);

    List<Appointment> getAppointmentsByFarmerId(Long farmerId);

    List<Appointment> getAppointmentsByVeterinarianId(Long veterinarianId);

    Appointment acceptAppointment(Long id);

    Appointment rejectAppointment(Long id);

    Appointment completeAppointment(Long id);

    Appointment cancelAppointment(Long id);
}