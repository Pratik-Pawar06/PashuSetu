package com.pashusetu.pashusetu.repository;

import com.pashusetu.pashusetu.entity.BreedingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BreedingRecordRepository extends JpaRepository<BreedingRecord, Long> {

    List<BreedingRecord> findByAnimalId(Long animalId);

    List<BreedingRecord> findByVeterinarianId(Long veterinarianId);

    List<BreedingRecord> findByPregnancyCheckDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

    List<BreedingRecord> findByExpectedCalvingDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

    List<BreedingRecord> findByPregnancyCheckDateBefore(LocalDate date);

    List<BreedingRecord> findByExpectedCalvingDateBefore(LocalDate date);
}