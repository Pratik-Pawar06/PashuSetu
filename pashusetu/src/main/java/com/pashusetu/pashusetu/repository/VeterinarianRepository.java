package com.pashusetu.pashusetu.repository;

import com.pashusetu.pashusetu.entity.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VeterinarianRepository
        extends JpaRepository<Veterinarian, Long> {

    Optional<Veterinarian> findByLicenseNumber(String licenseNumber);

    Optional<Veterinarian> findByUserId(Long userId);

    List<Veterinarian> findByDistrictId(Long districtId);

    List<Veterinarian> findByTalukaId(Long talukaId);

    List<Veterinarian> findByAvailableTrue();
}