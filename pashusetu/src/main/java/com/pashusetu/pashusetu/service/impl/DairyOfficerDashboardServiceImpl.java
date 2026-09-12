package com.pashusetu.pashusetu.service.impl;

import com.pashusetu.pashusetu.dto.DairyOfficerDashboardResponse;
import com.pashusetu.pashusetu.entity.BreedingStatus;
import com.pashusetu.pashusetu.entity.Role;
import com.pashusetu.pashusetu.entity.User;
import com.pashusetu.pashusetu.entity.Veterinarian;
import com.pashusetu.pashusetu.entity.VeterinarianType;
import com.pashusetu.pashusetu.entity.VerificationStatus;
import com.pashusetu.pashusetu.repository.BreedingRecordRepository;
import com.pashusetu.pashusetu.repository.DairyAnimalRepository;
import com.pashusetu.pashusetu.repository.FarmerRepository;
import com.pashusetu.pashusetu.repository.UserRepository;
import com.pashusetu.pashusetu.repository.VaccinationRepository;
import com.pashusetu.pashusetu.repository.VeterinarianRepository;
import com.pashusetu.pashusetu.service.DairyOfficerDashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class DairyOfficerDashboardServiceImpl
        implements DairyOfficerDashboardService {

    private final UserRepository userRepository;
    private final FarmerRepository farmerRepository;
    private final DairyAnimalRepository dairyAnimalRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final VaccinationRepository vaccinationRepository;
    private final BreedingRecordRepository breedingRecordRepository;

    public DairyOfficerDashboardServiceImpl(
            UserRepository userRepository,
            FarmerRepository farmerRepository,
            DairyAnimalRepository dairyAnimalRepository,
            VeterinarianRepository veterinarianRepository,
            VaccinationRepository vaccinationRepository,
            BreedingRecordRepository breedingRecordRepository) {

        this.userRepository = userRepository;
        this.farmerRepository = farmerRepository;
        this.dairyAnimalRepository = dairyAnimalRepository;
        this.veterinarianRepository = veterinarianRepository;
        this.vaccinationRepository = vaccinationRepository;
        this.breedingRecordRepository = breedingRecordRepository;
    }

    @Override
    public DairyOfficerDashboardResponse getDairyOfficerDashboard(
            Long officerId) {

        // Find Dairy Officer
        User officer = userRepository.findById(officerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Dairy Officer not found with id: "
                                        + officerId
                        )
                );

        // Verify that the user is actually a Dairy Officer
        if (officer.getRole() != Role.DAIRY_OFFICER) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "User is not a Dairy Officer"
            );
        }

        // Verify that the Dairy Officer is assigned to a district
        if (officer.getDistrict() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Dairy Officer is not assigned to a district"
            );
        }

        // Get Dairy Officer's district
        Long districtId = officer.getDistrict().getId();

        LocalDate today = LocalDate.now();
        LocalDate next7Days = today.plusDays(7);

        // ----------------------------------------
        // Farmers in this district
        // ----------------------------------------

        long totalFarmers =
                farmerRepository
                        .findByVillageTalukaDistrictId(districtId)
                        .size();

        // ----------------------------------------
        // Animals in this district
        // ----------------------------------------

        long totalAnimals =
                dairyAnimalRepository
                        .findByFarmerVillageTalukaDistrictId(districtId)
                        .size();

        // ----------------------------------------
        // Veterinarians in this district
        // ----------------------------------------

        List<Veterinarian> districtVeterinarians =
                veterinarianRepository.findByDistrictId(districtId);

        long totalVeterinarians =
                districtVeterinarians.size();

        long governmentVeterinarians =
                districtVeterinarians.stream()
                        .filter(v ->
                                v.getVeterinarianType()
                                        == VeterinarianType.GOVERNMENT
                        )
                        .count();

        long privateVeterinarians =
                districtVeterinarians.stream()
                        .filter(v ->
                                v.getVeterinarianType()
                                        == VeterinarianType.PRIVATE
                        )
                        .count();

        long approvedVeterinarians =
                districtVeterinarians.stream()
                        .filter(v ->
                                v.getVerificationStatus()
                                        == VerificationStatus.APPROVED
                        )
                        .count();

        long pendingVeterinarianVerification =
                districtVeterinarians.stream()
                        .filter(v ->
                                v.getVerificationStatus()
                                        == VerificationStatus.PENDING
                        )
                        .count();

        // ----------------------------------------
        // Upcoming vaccinations
        // ----------------------------------------

        long upcomingVaccinations =
                vaccinationRepository
                        .findByAnimalFarmerVillageTalukaDistrictIdAndNextDueDateBetween(
                                districtId,
                                today,
                                next7Days
                        )
                        .size();

        // ----------------------------------------
        // Overdue vaccinations
        // ----------------------------------------

        long overdueVaccinations =
                vaccinationRepository
                        .findByAnimalFarmerVillageTalukaDistrictIdAndNextDueDateBefore(
                                districtId,
                                today
                        )
                        .size();

        // ----------------------------------------
        // Active pregnancies
        // ----------------------------------------

        long activePregnancies =
                breedingRecordRepository
                        .findByAnimalFarmerVillageTalukaDistrictId(
                                districtId
                        )
                        .stream()
                        .filter(record ->
                                record.getBreedingStatus()
                                        == BreedingStatus.CONCEIVED
                        )
                        .count();

        // ----------------------------------------
        // Return dashboard response
        // ----------------------------------------

        return new DairyOfficerDashboardResponse(
                officerId,
                officer.getName(),
                totalFarmers,
                totalAnimals,
                totalVeterinarians,
                governmentVeterinarians,
                privateVeterinarians,
                approvedVeterinarians,
                pendingVeterinarianVerification,
                upcomingVaccinations,
                overdueVaccinations,
                activePregnancies
        );
    }
}