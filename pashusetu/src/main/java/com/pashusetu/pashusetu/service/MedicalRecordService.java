package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.entity.MedicalRecord;

import java.time.LocalDate;
import java.util.List;

public interface MedicalRecordService {

    MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);

    List<MedicalRecord> getAllMedicalRecords();

    MedicalRecord getMedicalRecordById(Long id);

    List<MedicalRecord> getMedicalRecordsByAnimalId(Long animalId);

    List<MedicalRecord> getMedicalRecordsByVeterinarianId(Long veterinarianId);

    List<MedicalRecord> getUpcomingFollowUps();

    List<MedicalRecord> getOverdueFollowUps();

    MedicalRecord updateMedicalRecord(
            Long id,
            MedicalRecord medicalRecord
    );

    void deleteMedicalRecord(Long id);
}