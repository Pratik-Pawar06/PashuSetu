package com.pashusetu.pashusetu.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.pashusetu.pashusetu.repository.NotificationRepository;
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
    private final NotificationRepository notificationRepository;

    public VaccinationServiceImpl(
            VaccinationRepository vaccinationRepository,
            DairyAnimalRepository dairyAnimalRepository,
            NotificationRepository notificationRepository) {

        this.vaccinationRepository = vaccinationRepository;
        this.dairyAnimalRepository = dairyAnimalRepository;
        this.notificationRepository = notificationRepository;
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
    public List<Vaccination> getUpcomingVaccinations() {

        LocalDate today = LocalDate.now();
        LocalDate nextSevenDays = today.plusDays(7);

        List<Vaccination> vaccinations =
                vaccinationRepository.findByNextDueDateBetween(
                        today,
                        nextSevenDays
                );

        for (Vaccination vaccination : vaccinations) {
            createVaccinationNotification(vaccination, "DUE");
        }

        return vaccinations;
    }

    @Override
    public List<Vaccination> getOverdueVaccinations() {

        LocalDate today = LocalDate.now();

        List<Vaccination> vaccinations =
                vaccinationRepository.findByNextDueDateBefore(today);

        for (Vaccination vaccination : vaccinations) {
            createVaccinationNotification(vaccination, "OVERDUE");
        }

        return vaccinations;
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
    private void createVaccinationNotification(
            Vaccination vaccination,
            String status) {

        DairyAnimal animal = vaccination.getAnimal();

        // Get the farmer's user directly
        com.pashusetu.pashusetu.entity.User user =
                animal.getFarmer().getUser();

        String message;

        if (status.equals("DUE")) {
            message = "Vaccination for " + animal.getName()
                    + " is due on "
                    + vaccination.getNextDueDate() + ".";
        } else {
            message = "Vaccination for " + animal.getName()
                    + " was due on "
                    + vaccination.getNextDueDate()
                    + " and is overdue.";
        }

        com.pashusetu.pashusetu.entity.Notification notification =
                new com.pashusetu.pashusetu.entity.Notification();

        notification.setMessage(message);
        notification.setType("VACCINATION_" + status);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUser(user);

        if (!notificationRepository.existsByUserIdAndMessage(user.getId(), message)) {
            notificationRepository.save(notification);
        }
    }
}