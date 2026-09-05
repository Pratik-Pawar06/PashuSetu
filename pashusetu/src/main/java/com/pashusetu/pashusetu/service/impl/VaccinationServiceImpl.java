package com.pashusetu.pashusetu.service.impl;

import com.pashusetu.pashusetu.entity.DairyAnimal;
import com.pashusetu.pashusetu.entity.Vaccination;
import com.pashusetu.pashusetu.repository.DairyAnimalRepository;
import com.pashusetu.pashusetu.repository.VaccinationRepository;
import com.pashusetu.pashusetu.service.VaccinationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccinationServiceImpl implements VaccinationService {

    private final VaccinationRepository vaccinationRepository;
    private final DairyAnimalRepository dairyAnimalRepository;

    public VaccinationServiceImpl(
            VaccinationRepository vaccinationRepository,
            DairyAnimalRepository dairyAnimalRepository) {

        this.vaccinationRepository = vaccinationRepository;
        this.dairyAnimalRepository = dairyAnimalRepository;
    }

    @Override
    public Vaccination addVaccination(Vaccination vaccination) {

        DairyAnimal animal = dairyAnimalRepository
                .findById(vaccination.getAnimal().getId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Animal not found with id: "
                                        + vaccination.getAnimal().getId()
                        )
                );

        vaccination.setAnimal(animal);

        return vaccinationRepository.save(vaccination);
    }

    @Override
    public List<Vaccination> getAllVaccinations() {
        return vaccinationRepository.findAll();
    }

    @Override
    public Vaccination getVaccinationById(Long id) {

        return vaccinationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Vaccination not found with id: " + id
                        )
                );
    }

    @Override
    public List<Vaccination> getVaccinationsByAnimalId(Long animalId) {

        // Verify animal exists
        dairyAnimalRepository.findById(animalId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Animal not found with id: " + animalId
                        )
                );

        return vaccinationRepository.findByAnimalId(animalId);
    }

    @Override
    public Vaccination updateVaccination(
            Long id,
            Vaccination vaccination) {

        Vaccination existingVaccination =
                getVaccinationById(id);

        DairyAnimal animal = dairyAnimalRepository
                .findById(vaccination.getAnimal().getId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Animal not found with id: "
                                        + vaccination.getAnimal().getId()
                        )
                );

        existingVaccination.setVaccineName(
                vaccination.getVaccineName()
        );

        existingVaccination.setVaccinationDate(
                vaccination.getVaccinationDate()
        );

        existingVaccination.setNextDueDate(
                vaccination.getNextDueDate()
        );

        existingVaccination.setAdministeredBy(
                vaccination.getAdministeredBy()
        );

        existingVaccination.setNotes(
                vaccination.getNotes()
        );

        existingVaccination.setAnimal(animal);

        return vaccinationRepository.save(existingVaccination);
    }

    @Override
    public void deleteVaccination(Long id) {

        Vaccination vaccination =
                getVaccinationById(id);

        vaccinationRepository.delete(vaccination);
    }
}