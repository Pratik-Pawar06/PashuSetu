package com.pashusetu.pashusetu.repository;

import com.pashusetu.pashusetu.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MedicalRecordRepository
        extends JpaRepository<MedicalRecord, Long> {

    // Get complete medical history of an animal
    List<MedicalRecord> findByAnimalId(Long animalId);

    // Get all records created by a veterinarian
    List<MedicalRecord> findByVeterinarianId(Long veterinarianId);

    // Get follow-ups due within a date range
    List<MedicalRecord> findByFollowUpDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

    // Get overdue follow-ups
    List<MedicalRecord> findByFollowUpDateBefore(
            LocalDate date
    );
}