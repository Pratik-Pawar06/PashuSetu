package com.pashusetu.pashusetu.repository;

import com.pashusetu.pashusetu.entity.Veterinarian;
import com.pashusetu.pashusetu.entity.VeterinarianType;
import com.pashusetu.pashusetu.entity.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VeterinarianRepository
        extends JpaRepository<Veterinarian, Long> {

    Optional<Veterinarian> findByLicenseNumber(
            String licenseNumber
    );

    Optional<Veterinarian> findByUserId(
            Long userId
    );

    List<Veterinarian> findByDistrictId(
            Long districtId
    );

    List<Veterinarian> findByTalukaId(
            Long talukaId
    );

    List<Veterinarian> findByAvailableTrue();


    // Approved veterinarians
    List<Veterinarian> findByVerificationStatus(
            VerificationStatus verificationStatus
    );


    // Approved veterinarians in a particular taluka
    List<Veterinarian> findByTalukaIdAndVerificationStatus(
            Long talukaId,
            VerificationStatus verificationStatus
    );


    // Approved vets by taluka and type
    List<Veterinarian> findByTalukaIdAndVerificationStatusAndVeterinarianType(
            Long talukaId,
            VerificationStatus verificationStatus,
            VeterinarianType veterinarianType
    );


    // Approved and available vets by taluka
    List<Veterinarian>
    findByTalukaIdAndVerificationStatusAndAvailableTrue(
            Long talukaId,
            VerificationStatus verificationStatus
    );
}