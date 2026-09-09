package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.entity.BreedingRecord;

import java.util.List;

public interface BreedingRecordService {

    BreedingRecord addBreedingRecord(BreedingRecord breedingRecord);

    List<BreedingRecord> getAllBreedingRecords();

    BreedingRecord getBreedingRecordById(Long id);

    List<BreedingRecord> getBreedingRecordsByAnimalId(Long animalId);

    List<BreedingRecord> getBreedingRecordsByVeterinarianId(Long veterinarianId);

    List<BreedingRecord> getUpcomingPregnancyChecks();

    List<BreedingRecord> getUpcomingCalvingDates();

    List<BreedingRecord> getOverduePregnancyChecks();

    List<BreedingRecord> getOverdueCalvingDates();

    BreedingRecord updateBreedingRecord(Long id, BreedingRecord breedingRecord);

    void deleteBreedingRecord(Long id);
}