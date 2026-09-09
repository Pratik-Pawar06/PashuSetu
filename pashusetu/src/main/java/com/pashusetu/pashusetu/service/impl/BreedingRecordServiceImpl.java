package com.pashusetu.pashusetu.service.impl;

import com.pashusetu.pashusetu.entity.BreedingRecord;
import com.pashusetu.pashusetu.entity.DairyAnimal;
import com.pashusetu.pashusetu.entity.Veterinarian;
import com.pashusetu.pashusetu.repository.BreedingRecordRepository;
import com.pashusetu.pashusetu.repository.DairyAnimalRepository;
import com.pashusetu.pashusetu.repository.VeterinarianRepository;
import com.pashusetu.pashusetu.service.BreedingRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class BreedingRecordServiceImpl implements BreedingRecordService {

    private final BreedingRecordRepository breedingRecordRepository;
    private final DairyAnimalRepository dairyAnimalRepository;
    private final VeterinarianRepository veterinarianRepository;

    public BreedingRecordServiceImpl(
            BreedingRecordRepository breedingRecordRepository,
            DairyAnimalRepository dairyAnimalRepository,
            VeterinarianRepository veterinarianRepository) {

        this.breedingRecordRepository = breedingRecordRepository;
        this.dairyAnimalRepository = dairyAnimalRepository;
        this.veterinarianRepository = veterinarianRepository;
    }

    @Override
    public BreedingRecord addBreedingRecord(BreedingRecord breedingRecord) {

        DairyAnimal animal = dairyAnimalRepository.findById(
                breedingRecord.getAnimal().getId()
        ).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Animal not found with id: " +
                        breedingRecord.getAnimal().getId()
        ));

        Veterinarian veterinarian = veterinarianRepository.findById(
                breedingRecord.getVeterinarian().getId()
        ).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Veterinarian not found with id: " +
                        breedingRecord.getVeterinarian().getId()
        ));

        breedingRecord.setAnimal(animal);
        breedingRecord.setVeterinarian(veterinarian);

        return breedingRecordRepository.save(breedingRecord);
    }

    @Override
    public List<BreedingRecord> getAllBreedingRecords() {
        return breedingRecordRepository.findAll();
    }

    @Override
    public BreedingRecord getBreedingRecordById(Long id) {

        return breedingRecordRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Breeding record not found with id: " + id
                ));
    }

    @Override
    public List<BreedingRecord> getBreedingRecordsByAnimalId(Long animalId) {

        dairyAnimalRepository.findById(animalId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Animal not found with id: " + animalId
                ));

        return breedingRecordRepository.findByAnimalId(animalId);
    }

    @Override
    public List<BreedingRecord> getBreedingRecordsByVeterinarianId(Long veterinarianId) {

        veterinarianRepository.findById(veterinarianId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Veterinarian not found with id: " + veterinarianId
                ));

        return breedingRecordRepository.findByVeterinarianId(veterinarianId);
    }

    @Override
    public List<BreedingRecord> getUpcomingPregnancyChecks() {

        LocalDate today = LocalDate.now();
        LocalDate nextSevenDays = today.plusDays(7);

        return breedingRecordRepository.findByPregnancyCheckDateBetween(
                today,
                nextSevenDays
        );
    }

    @Override
    public List<BreedingRecord> getUpcomingCalvingDates() {

        LocalDate today = LocalDate.now();
        LocalDate nextSevenDays = today.plusDays(7);

        return breedingRecordRepository.findByExpectedCalvingDateBetween(
                today,
                nextSevenDays
        );
    }

    @Override
    public List<BreedingRecord> getOverduePregnancyChecks() {

        LocalDate today = LocalDate.now();

        return breedingRecordRepository.findByPregnancyCheckDateBefore(today);
    }

    @Override
    public List<BreedingRecord> getOverdueCalvingDates() {

        LocalDate today = LocalDate.now();

        return breedingRecordRepository.findByExpectedCalvingDateBefore(today);
    }

    @Override
    public BreedingRecord updateBreedingRecord(
            Long id,
            BreedingRecord breedingRecord) {

        BreedingRecord existingRecord = getBreedingRecordById(id);

        DairyAnimal animal = dairyAnimalRepository.findById(
                breedingRecord.getAnimal().getId()
        ).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Animal not found with id: " +
                        breedingRecord.getAnimal().getId()
        ));

        Veterinarian veterinarian = veterinarianRepository.findById(
                breedingRecord.getVeterinarian().getId()
        ).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Veterinarian not found with id: " +
                        breedingRecord.getVeterinarian().getId()
        ));

        existingRecord.setAnimal(animal);
        existingRecord.setVeterinarian(veterinarian);
        existingRecord.setServiceDate(breedingRecord.getServiceDate());
        existingRecord.setBreedingMethod(breedingRecord.getBreedingMethod());
        existingRecord.setPregnancyCheckDate(
                breedingRecord.getPregnancyCheckDate()
        );
        existingRecord.setBreedingStatus(
                breedingRecord.getBreedingStatus()
        );
        existingRecord.setExpectedCalvingDate(
                breedingRecord.getExpectedCalvingDate()
        );
        existingRecord.setActualCalvingDate(
                breedingRecord.getActualCalvingDate()
        );
        existingRecord.setNotes(breedingRecord.getNotes());

        return breedingRecordRepository.save(existingRecord);
    }

    @Override
    public void deleteBreedingRecord(Long id) {

        BreedingRecord breedingRecord = getBreedingRecordById(id);

        breedingRecordRepository.delete(breedingRecord);
    }
}