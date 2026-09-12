package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.entity.Appointment;
import com.pashusetu.pashusetu.entity.AppointmentStatus;
import com.pashusetu.pashusetu.entity.BreedingRecord;
import com.pashusetu.pashusetu.entity.MedicalRecord;
import com.pashusetu.pashusetu.entity.Notification;
import com.pashusetu.pashusetu.entity.Vaccination;
import com.pashusetu.pashusetu.repository.AppointmentRepository;
import com.pashusetu.pashusetu.repository.BreedingRecordRepository;
import com.pashusetu.pashusetu.repository.MedicalRecordRepository;
import com.pashusetu.pashusetu.repository.VaccinationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReminderScheduler {

    private final VaccinationRepository vaccinationRepository;
    private final BreedingRecordRepository breedingRecordRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final AppointmentRepository appointmentRepository;
    private final NotificationService notificationService;

    public ReminderScheduler(
            VaccinationRepository vaccinationRepository,
            BreedingRecordRepository breedingRecordRepository,
            MedicalRecordRepository medicalRecordRepository,
            AppointmentRepository appointmentRepository,
            NotificationService notificationService) {

        this.vaccinationRepository = vaccinationRepository;
        this.breedingRecordRepository = breedingRecordRepository;
        this.medicalRecordRepository = medicalRecordRepository;
        this.appointmentRepository = appointmentRepository;
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void checkReminders() {

        LocalDate today = LocalDate.now();

        // =====================================================
        // 1. VACCINATION REMINDERS
        // =====================================================

        List<Vaccination> upcomingVaccinations =
                vaccinationRepository.findByNextDueDateBetween(
                        today,
                        today.plusDays(7)
                );

        for (Vaccination vaccination : upcomingVaccinations) {

            Notification notification = new Notification();

            notification.setMessage(
                    "Vaccination reminder: " +
                            vaccination.getVaccineName() +
                            " is due on " +
                            vaccination.getNextDueDate()
            );

            notification.setType("VACCINATION_REMINDER");
            notification.setRead(false);
            notification.setCreatedAt(LocalDateTime.now());

            notification.setUser(
                    vaccination.getAnimal()
                            .getFarmer()
                            .getUser()
            );

            notificationService.createNotification(notification);
        }

        // Overdue vaccinations
        List<Vaccination> overdueVaccinations =
                vaccinationRepository.findByNextDueDateBefore(today);

        for (Vaccination vaccination : overdueVaccinations) {

            Notification notification = new Notification();

            notification.setMessage(
                    "Vaccination overdue: " +
                            vaccination.getVaccineName() +
                            " was due on " +
                            vaccination.getNextDueDate()
            );

            notification.setType("VACCINATION_OVERDUE");
            notification.setRead(false);
            notification.setCreatedAt(LocalDateTime.now());

            notification.setUser(
                    vaccination.getAnimal()
                            .getFarmer()
                            .getUser()
            );

            notificationService.createNotification(notification);
        }


        // =====================================================
        // 2. PREGNANCY CHECK REMINDERS
        // =====================================================

        List<BreedingRecord> upcomingPregnancyChecks =
                breedingRecordRepository.findByPregnancyCheckDateBetween(
                        today,
                        today.plusDays(7)
                );

        for (BreedingRecord record : upcomingPregnancyChecks) {

            Notification notification = new Notification();

            notification.setMessage(
                    "Pregnancy check-up reminder: " +
                            "Pregnancy check-up is due on " +
                            record.getPregnancyCheckDate()
            );

            notification.setType("PREGNANCY_CHECK_REMINDER");
            notification.setRead(false);
            notification.setCreatedAt(LocalDateTime.now());

            notification.setUser(
                    record.getAnimal()
                            .getFarmer()
                            .getUser()
            );

            notificationService.createNotification(notification);
        }


        // =====================================================
        // 3. EXPECTED CALVING REMINDERS
        // =====================================================

        List<BreedingRecord> upcomingCalvings =
                breedingRecordRepository.findByExpectedCalvingDateBetween(
                        today,
                        today.plusDays(7)
                );

        for (BreedingRecord record : upcomingCalvings) {

            if (record.getExpectedCalvingDate() == null) {
                continue;
            }

            Notification notification = new Notification();

            notification.setMessage(
                    "Expected calving reminder: " +
                            "Calving is expected on " +
                            record.getExpectedCalvingDate()
            );

            notification.setType("CALVING_REMINDER");
            notification.setRead(false);
            notification.setCreatedAt(LocalDateTime.now());

            notification.setUser(
                    record.getAnimal()
                            .getFarmer()
                            .getUser()
            );

            notificationService.createNotification(notification);
        }


        // =====================================================
        // 4. MEDICAL FOLLOW-UP REMINDERS
        // =====================================================

        List<MedicalRecord> upcomingFollowUps =
                medicalRecordRepository.findByFollowUpDateBetween(
                        today,
                        today.plusDays(7)
                );

        for (MedicalRecord record : upcomingFollowUps) {

            if (record.getFollowUpDate() == null) {
                continue;
            }

            Notification notification = new Notification();

            notification.setMessage(
                    "Medical follow-up reminder: " +
                            "Follow-up check-up is due on " +
                            record.getFollowUpDate()
            );

            notification.setType("MEDICAL_FOLLOW_UP_REMINDER");
            notification.setRead(false);
            notification.setCreatedAt(LocalDateTime.now());

            notification.setUser(
                    record.getAnimal()
                            .getFarmer()
                            .getUser()
            );

            notificationService.createNotification(notification);
        }


        // =====================================================
        // 5. APPOINTMENT REMINDERS
        // =====================================================

        List<Appointment> upcomingAppointments =
                appointmentRepository.findByAppointmentDateBetween(
                        today,
                        today.plusDays(7)
                );

        for (Appointment appointment : upcomingAppointments) {

            if (appointment.getStatus() != AppointmentStatus.PENDING &&
                    appointment.getStatus() != AppointmentStatus.ACCEPTED) {
                continue;
            }

            Notification notification = new Notification();

            notification.setMessage(
                    "Appointment reminder: " +
                            "Your veterinary appointment is scheduled on " +
                            appointment.getAppointmentDate() +
                            " at " +
                            appointment.getAppointmentTime()
            );

            notification.setType("APPOINTMENT_REMINDER");
            notification.setRead(false);
            notification.setCreatedAt(LocalDateTime.now());

            notification.setUser(
                    appointment.getFarmer()
                            .getUser()
            );

            notificationService.createNotification(notification);
        }
    }
}