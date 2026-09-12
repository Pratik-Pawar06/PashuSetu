package com.pashusetu.pashusetu.service.impl;

import com.pashusetu.pashusetu.dto.FarmerDashboardResponse;
import com.pashusetu.pashusetu.entity.BreedingStatus;
import com.pashusetu.pashusetu.entity.Farmer;
import com.pashusetu.pashusetu.repository.AppointmentRepository;
import com.pashusetu.pashusetu.repository.BreedingRecordRepository;
import com.pashusetu.pashusetu.repository.DairyAnimalRepository;
import com.pashusetu.pashusetu.repository.FarmerRepository;
import com.pashusetu.pashusetu.repository.NotificationRepository;
import com.pashusetu.pashusetu.repository.VaccinationRepository;
import com.pashusetu.pashusetu.service.FarmerDashboardService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FarmerDashboardServiceImpl implements FarmerDashboardService {

    private final FarmerRepository farmerRepository;
    private final DairyAnimalRepository dairyAnimalRepository;
    private final VaccinationRepository vaccinationRepository;
    private final AppointmentRepository appointmentRepository;
    private final BreedingRecordRepository breedingRecordRepository;
    private final NotificationRepository notificationRepository;

    public FarmerDashboardServiceImpl(
            FarmerRepository farmerRepository,
            DairyAnimalRepository dairyAnimalRepository,
            VaccinationRepository vaccinationRepository,
            AppointmentRepository appointmentRepository,
            BreedingRecordRepository breedingRecordRepository,
            NotificationRepository notificationRepository) {

        this.farmerRepository = farmerRepository;
        this.dairyAnimalRepository = dairyAnimalRepository;
        this.vaccinationRepository = vaccinationRepository;
        this.appointmentRepository = appointmentRepository;
        this.breedingRecordRepository = breedingRecordRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public FarmerDashboardResponse getFarmerDashboard(Long farmerId) {

        Farmer farmer = farmerRepository.findById(farmerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Farmer not found with id: " + farmerId
                        ));

        LocalDate today = LocalDate.now();
        LocalDate next7Days = today.plusDays(7);

        long totalAnimals =
                dairyAnimalRepository.findByFarmerId(farmerId).size();

        long upcomingVaccinations =
                vaccinationRepository
                        .findByAnimalFarmerIdAndNextDueDateBetween(
                                farmerId,
                                today,
                                next7Days
                        )
                        .size();

        long overdueVaccinations =
                vaccinationRepository
                        .findByAnimalFarmerIdAndNextDueDateBefore(
                                farmerId,
                                today
                        )
                        .size();

        long upcomingAppointments =
                appointmentRepository
                        .findByFarmerIdAndAppointmentDateBetween(
                                farmerId,
                                today,
                                next7Days
                        )
                        .size();

        long activePregnancies =
                breedingRecordRepository
                        .findByAnimalFarmerIdAndBreedingStatus(
                                farmerId,
                                BreedingStatus.CONCEIVED
                        )
                        .size();

        long unreadNotifications =
                notificationRepository
                        .findByUserIdAndIsReadFalse(
                                farmer.getUser().getId()
                        )
                        .size();

        return new FarmerDashboardResponse(
                farmerId,
                farmer.getUser().getName(),
                totalAnimals,
                upcomingVaccinations,
                overdueVaccinations,
                upcomingAppointments,
                activePregnancies,
                unreadNotifications
        );
    }
}