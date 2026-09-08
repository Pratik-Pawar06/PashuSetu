package com.pashusetu.pashusetu.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.pashusetu.pashusetu.entity.DairyAnimal;
import com.pashusetu.pashusetu.entity.MedicalRecord;
import com.pashusetu.pashusetu.entity.Veterinarian;
import com.pashusetu.pashusetu.repository.DairyAnimalRepository;
import com.pashusetu.pashusetu.repository.MedicalRecordRepository;
import com.pashusetu.pashusetu.repository.VeterinarianRepository;
import com.pashusetu.pashusetu.service.MedicalRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final DairyAnimalRepository dairyAnimalRepository;
    private final VeterinarianRepository veterinarianRepository;

    public MedicalRecordServiceImpl(
            MedicalRecordRepository medicalRecordRepository,
            DairyAnimalRepository dairyAnimalRepository,
            VeterinarianRepository veterinarianRepository) {

        this.medicalRecordRepository = medicalRecordRepository;
        this.dairyAnimalRepository = dairyAnimalRepository;
        this.veterinarianRepository = veterinarianRepository;
    }


    @Override
    public MedicalRecord addMedicalRecord(
            MedicalRecord medicalRecord) {

        DairyAnimal animal = dairyAnimalRepository.findById(
                medicalRecord.getAnimal().getId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Animal not found with id: "
                                + medicalRecord.getAnimal().getId()
                )
        );

        Veterinarian veterinarian = veterinarianRepository.findById(
                medicalRecord.getVeterinarian().getId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Veterinarian not found with id: "
                                + medicalRecord.getVeterinarian().getId()
                )
        );

        medicalRecord.setAnimal(animal);
        medicalRecord.setVeterinarian(veterinarian);

        return medicalRecordRepository.save(medicalRecord);
    }


    @Override
    public List<MedicalRecord> getAllMedicalRecords() {

        return medicalRecordRepository.findAll();
    }


    @Override
    public MedicalRecord getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Medical record not found with id: " + id
                ));
    }


    @Override
    public List<MedicalRecord> getMedicalRecordsByAnimalId(
            Long animalId) {

        dairyAnimalRepository.findById(animalId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Animal not found with id: " + animalId
                        )
                );

        return medicalRecordRepository.findByAnimalId(animalId);
    }


    @Override
    public List<MedicalRecord> getMedicalRecordsByVeterinarianId(
            Long veterinarianId) {

        veterinarianRepository.findById(veterinarianId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Veterinarian not found with id: "
                                        + veterinarianId
                        )
                );

        return medicalRecordRepository
                .findByVeterinarianId(veterinarianId);
    }


    @Override
    public List<MedicalRecord> getUpcomingFollowUps() {

        LocalDate today = LocalDate.now();
        LocalDate nextSevenDays = today.plusDays(7);

        return medicalRecordRepository
                .findByFollowUpDateBetween(
                        today,
                        nextSevenDays
                );
    }


    @Override
    public List<MedicalRecord> getOverdueFollowUps() {

        LocalDate today = LocalDate.now();

        return medicalRecordRepository
                .findByFollowUpDateBefore(today);
    }


    @Override
    public MedicalRecord updateMedicalRecord(
            Long id,
            MedicalRecord medicalRecord) {

        MedicalRecord existingRecord =
                getMedicalRecordById(id);

        DairyAnimal animal = dairyAnimalRepository.findById(
                medicalRecord.getAnimal().getId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Animal not found with id: "
                                + medicalRecord.getAnimal().getId()
                )
        );

        Veterinarian veterinarian = veterinarianRepository.findById(
                medicalRecord.getVeterinarian().getId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Veterinarian not found with id: "
                                + medicalRecord.getVeterinarian().getId()
                )
        );


        existingRecord.setAnimal(animal);
        existingRecord.setVeterinarian(veterinarian);

        existingRecord.setVisitDate(
                medicalRecord.getVisitDate()
        );

        existingRecord.setSymptoms(
                medicalRecord.getSymptoms()
        );

        existingRecord.setDiagnosis(
                medicalRecord.getDiagnosis()
        );

        existingRecord.setTreatment(
                medicalRecord.getTreatment()
        );

        existingRecord.setMedicines(
                medicalRecord.getMedicines()
        );

        existingRecord.setNotes(
                medicalRecord.getNotes()
        );

        existingRecord.setFollowUpDate(
                medicalRecord.getFollowUpDate()
        );


        return medicalRecordRepository.save(existingRecord);
    }


    @Override
    public void deleteMedicalRecord(Long id) {

        MedicalRecord medicalRecord =
                getMedicalRecordById(id);

        medicalRecordRepository.delete(medicalRecord);
    }
}