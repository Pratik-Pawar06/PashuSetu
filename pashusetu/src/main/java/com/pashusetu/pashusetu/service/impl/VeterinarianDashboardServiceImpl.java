package com.pashusetu.pashusetu.service.impl;

import com.pashusetu.pashusetu.dto.VeterinarianDashboardResponse;
import com.pashusetu.pashusetu.entity.AppointmentStatus;
import com.pashusetu.pashusetu.entity.BreedingStatus;
import com.pashusetu.pashusetu.entity.Veterinarian;
import com.pashusetu.pashusetu.repository.AppointmentRepository;
import com.pashusetu.pashusetu.repository.BreedingRecordRepository;
import com.pashusetu.pashusetu.repository.MedicalRecordRepository;
import com.pashusetu.pashusetu.repository.VeterinarianRepository;
import com.pashusetu.pashusetu.service.VeterinarianDashboardService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VeterinarianDashboardServiceImpl
        implements VeterinarianDashboardService {

    private final VeterinarianRepository veterinarianRepository;
    private final AppointmentRepository appointmentRepository;
    private final BreedingRecordRepository breedingRecordRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public VeterinarianDashboardServiceImpl(
            VeterinarianRepository veterinarianRepository,
            AppointmentRepository appointmentRepository,
            BreedingRecordRepository breedingRecordRepository,
            MedicalRecordRepository medicalRecordRepository) {

        this.veterinarianRepository = veterinarianRepository;
        this.appointmentRepository = appointmentRepository;
        this.breedingRecordRepository = breedingRecordRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public VeterinarianDashboardResponse getVeterinarianDashboard(
            Long veterinarianId) {

        Veterinarian veterinarian =
                veterinarianRepository.findById(veterinarianId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Veterinarian not found with id: "
                                                + veterinarianId));

        LocalDate today = LocalDate.now();
        LocalDate next7Days = today.plusDays(7);

        long todayAppointments =
                appointmentRepository
                        .findByVeterinarianIdAndAppointmentDate(
                                veterinarianId,
                                today
                        )
                        .size();

        long pendingAppointments =
                appointmentRepository
                        .findByVeterinarianId(veterinarianId)
                        .stream()
                        .filter(a -> a.getStatus()
                                == AppointmentStatus.PENDING)
                        .count();

        long acceptedAppointments =
                appointmentRepository
                        .findByVeterinarianId(veterinarianId)
                        .stream()
                        .filter(a -> a.getStatus()
                                == AppointmentStatus.ACCEPTED)
                        .count();

        long completedAppointments =
                appointmentRepository
                        .findByVeterinarianId(veterinarianId)
                        .stream()
                        .filter(a -> a.getStatus()
                                == AppointmentStatus.COMPLETED)
                        .count();

        long upcomingAppointments =
                appointmentRepository
                        .findByVeterinarianId(veterinarianId)
                        .stream()
                        .filter(a ->
                                a.getAppointmentDate()
                                        .isAfter(today)
                                        && !a.getAppointmentDate()
                                        .isAfter(next7Days)
                        )
                        .count();

        long pregnancyCheckups =
                breedingRecordRepository
                        .findByVeterinarianId(veterinarianId)
                        .stream()
                        .filter(b ->
                                b.getPregnancyCheckDate() != null
                                        && !b.getPregnancyCheckDate()
                                        .isBefore(today)
                        )
                        .count();

        long followUps =
                medicalRecordRepository
                        .findByVeterinarianId(veterinarianId)
                        .stream()
                        .filter(m ->
                                m.getFollowUpDate() != null
                                        && !m.getFollowUpDate()
                                        .isBefore(today)
                        )
                        .count();

        return new VeterinarianDashboardResponse(
                veterinarianId,
                veterinarian.getUser().getName(),
                todayAppointments,
                pendingAppointments,
                acceptedAppointments,
                completedAppointments,
                upcomingAppointments,
                pregnancyCheckups,
                followUps,
                veterinarian.isAvailable()
        );
    }
}