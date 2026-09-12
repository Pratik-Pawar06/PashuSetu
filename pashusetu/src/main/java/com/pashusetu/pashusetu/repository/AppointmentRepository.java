package com.pashusetu.pashusetu.repository;

import com.pashusetu.pashusetu.entity.Appointment;
import com.pashusetu.pashusetu.entity.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByFarmerId(Long farmerId);

    List<Appointment> findByFarmerIdAndAppointmentDateBetween(
            Long farmerId,
            LocalDate startDate,
            LocalDate endDate
    );

    List<Appointment> findByFarmerIdAndStatus(
            Long farmerId,
            AppointmentStatus status
    );

    List<Appointment> findByVeterinarianId(Long veterinarianId);

    List<Appointment> findByAnimalId(Long animalId);

    List<Appointment> findByStatus(AppointmentStatus status);

    List<Appointment> findByVeterinarianIdAndAppointmentDate(
            Long veterinarianId,
            LocalDate appointmentDate
    );

    List<Appointment> findByAppointmentDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

    boolean existsByVeterinarianIdAndAppointmentDateAndAppointmentTimeAndStatusIn(
            Long veterinarianId,
            LocalDate appointmentDate,
            LocalTime appointmentTime,
            List<AppointmentStatus> statuses
    );
}