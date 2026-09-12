package com.pashusetu.pashusetu.repository;

import com.pashusetu.pashusetu.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VaccinationRepository
        extends JpaRepository<Vaccination, Long> {

    List<Vaccination> findByAnimalId(Long animalId);

    List<Vaccination> findByNextDueDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

    List<Vaccination> findByNextDueDateBefore(
            LocalDate date
    );

    List<Vaccination> findByAnimalFarmerIdAndNextDueDateBetween(
            Long farmerId,
            LocalDate startDate,
            LocalDate endDate
    );

    List<Vaccination> findByAnimalFarmerIdAndNextDueDateBefore(
            Long farmerId,
            LocalDate date
    );

    List<Vaccination> findByAnimalFarmerVillageTalukaDistrictIdAndNextDueDateBetween(
            Long districtId,
            LocalDate startDate,
            LocalDate endDate
    );

    List<Vaccination> findByAnimalFarmerVillageTalukaDistrictIdAndNextDueDateBefore(
            Long districtId,
            LocalDate date
    );
}