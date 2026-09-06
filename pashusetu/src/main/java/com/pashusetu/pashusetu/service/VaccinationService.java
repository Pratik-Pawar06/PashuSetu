package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.entity.Vaccination;

import java.util.List;

public interface VaccinationService {

    Vaccination addVaccination(Vaccination vaccination);

    List<Vaccination> getAllVaccinations();

    Vaccination getVaccinationById(Long id);

    List<Vaccination> getVaccinationsByAnimalId(Long animalId);

    List<Vaccination> getUpcomingVaccinations();

    List<Vaccination> getOverdueVaccinations();

    Vaccination updateVaccination(Long id, Vaccination vaccination);

    void deleteVaccination(Long id);
}