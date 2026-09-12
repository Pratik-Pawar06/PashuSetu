package com.pashusetu.pashusetu.service.impl;

import com.pashusetu.pashusetu.dto.SuperAdminDashboardResponse;
import com.pashusetu.pashusetu.entity.Role;
import com.pashusetu.pashusetu.entity.VeterinarianType;
import com.pashusetu.pashusetu.entity.VerificationStatus;
import com.pashusetu.pashusetu.repository.UserRepository;
import com.pashusetu.pashusetu.repository.VeterinarianRepository;
import com.pashusetu.pashusetu.service.SuperAdminDashboardService;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminDashboardServiceImpl
        implements SuperAdminDashboardService {

    private final UserRepository userRepository;
    private final VeterinarianRepository veterinarianRepository;

    public SuperAdminDashboardServiceImpl(
            UserRepository userRepository,
            VeterinarianRepository veterinarianRepository) {

        this.userRepository = userRepository;
        this.veterinarianRepository = veterinarianRepository;
    }

    @Override
    public SuperAdminDashboardResponse getSuperAdminDashboard() {

        long totalUsers = userRepository.count();

        long totalFarmers =
                userRepository.findAll()
                        .stream()
                        .filter(u -> u.getRole() == Role.FARMER)
                        .count();

        long totalVeterinarians =
                userRepository.findAll()
                        .stream()
                        .filter(u -> u.getRole() == Role.VETERINARIAN)
                        .count();

        long totalDairyOfficers =
                userRepository.findAll()
                        .stream()
                        .filter(u -> u.getRole() == Role.DAIRY_OFFICER)
                        .count();

        long totalSuperAdmins =
                userRepository.findAll()
                        .stream()
                        .filter(u -> u.getRole() == Role.SUPER_ADMIN)
                        .count();

        long governmentVeterinarians =
                veterinarianRepository.findAll()
                        .stream()
                        .filter(v -> v.getVeterinarianType()
                                == VeterinarianType.GOVERNMENT)
                        .count();

        long privateVeterinarians =
                veterinarianRepository.findAll()
                        .stream()
                        .filter(v -> v.getVeterinarianType()
                                == VeterinarianType.PRIVATE)
                        .count();

        long approvedVeterinarians =
                veterinarianRepository
                        .findByVerificationStatus(
                                VerificationStatus.APPROVED)
                        .size();

        long pendingVeterinarianVerification =
                veterinarianRepository
                        .findByVerificationStatus(
                                VerificationStatus.PENDING)
                        .size();

        long rejectedVeterinarians =
                veterinarianRepository
                        .findByVerificationStatus(
                                VerificationStatus.REJECTED)
                        .size();

        return new SuperAdminDashboardResponse(
                totalUsers,
                totalFarmers,
                totalVeterinarians,
                totalDairyOfficers,
                totalSuperAdmins,
                governmentVeterinarians,
                privateVeterinarians,
                approvedVeterinarians,
                pendingVeterinarianVerification,
                rejectedVeterinarians
        );
    }
}