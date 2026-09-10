package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.dto.AppointmentRequest;
import com.pashusetu.pashusetu.entity.Appointment;
import com.pashusetu.pashusetu.entity.AppointmentStatus;
import com.pashusetu.pashusetu.entity.Availability;
import com.pashusetu.pashusetu.entity.DairyAnimal;
import com.pashusetu.pashusetu.entity.Farmer;
import com.pashusetu.pashusetu.entity.Veterinarian;
import com.pashusetu.pashusetu.repository.AppointmentRepository;
import com.pashusetu.pashusetu.repository.AvailabilityRepository;
import com.pashusetu.pashusetu.repository.DairyAnimalRepository;
import com.pashusetu.pashusetu.repository.FarmerRepository;
import com.pashusetu.pashusetu.repository.VeterinarianRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final FarmerRepository farmerRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final DairyAnimalRepository dairyAnimalRepository;
    private final AvailabilityRepository availabilityRepository;

    public AppointmentServiceImpl(
            AppointmentRepository appointmentRepository,
            FarmerRepository farmerRepository,
            VeterinarianRepository veterinarianRepository,
            DairyAnimalRepository dairyAnimalRepository,
            AvailabilityRepository availabilityRepository) {

        this.appointmentRepository = appointmentRepository;
        this.farmerRepository = farmerRepository;
        this.veterinarianRepository = veterinarianRepository;
        this.dairyAnimalRepository = dairyAnimalRepository;
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public Appointment createAppointment(AppointmentRequest request) {

        Farmer farmer = farmerRepository.findById(request.getFarmerId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Farmer not found"
                ));

        Veterinarian veterinarian = veterinarianRepository
                .findById(request.getVeterinarianId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Veterinarian not found"
                ));

        DairyAnimal animal = dairyAnimalRepository
                .findById(request.getAnimalId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Animal not found"
                ));

        // Check whether veterinarian is available
        if (!Boolean.TRUE.equals(veterinarian.isAvailable())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Veterinarian is currently unavailable"
            );
        }

        // Check veterinarian's day-wise availability
        DayOfWeek dayOfWeek =
                request.getAppointmentDate().getDayOfWeek();

        Availability availability = availabilityRepository
                .findByVeterinarianIdAndDayOfWeekAndAvailableTrue(
                        veterinarian.getId(),
                        dayOfWeek
                )
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Veterinarian is not available on this day"
                ));

        // Check appointment time
        LocalTime appointmentTime =
                request.getAppointmentTime();

        if (appointmentTime.isBefore(availability.getStartTime())
                || appointmentTime.isAfter(availability.getEndTime())) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Appointment time is outside veterinarian's available hours"
            );
        }

        // Prevent double booking
        List<AppointmentStatus> activeStatuses = List.of(
                AppointmentStatus.PENDING,
                AppointmentStatus.ACCEPTED
        );

        boolean alreadyBooked =
                appointmentRepository
                        .existsByVeterinarianIdAndAppointmentDateAndAppointmentTimeAndStatusIn(
                                veterinarian.getId(),
                                request.getAppointmentDate(),
                                request.getAppointmentTime(),
                                activeStatuses
                        );

        if (alreadyBooked) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "This time slot is already booked"
            );
        }

        // Create appointment
        Appointment appointment = new Appointment();

        appointment.setFarmer(farmer);
        appointment.setVeterinarian(veterinarian);
        appointment.setAnimal(animal);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setReason(request.getReason());
        appointment.setNotes(request.getNotes());

        // New appointment starts as PENDING
        appointment.setStatus(AppointmentStatus.PENDING);

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Appointment not found"
                ));
    }

    @Override
    public List<Appointment> getAppointmentsByFarmerId(Long farmerId) {
        return appointmentRepository.findByFarmerId(farmerId);
    }

    @Override
    public List<Appointment> getAppointmentsByVeterinarianId(Long veterinarianId) {
        return appointmentRepository.findByVeterinarianId(veterinarianId);
    }

    @Override
    public Appointment acceptAppointment(Long id) {

        Appointment appointment = getAppointmentById(id);

        appointment.setStatus(AppointmentStatus.ACCEPTED);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment rejectAppointment(Long id) {

        Appointment appointment = getAppointmentById(id);

        appointment.setStatus(AppointmentStatus.REJECTED);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment completeAppointment(Long id) {

        Appointment appointment = getAppointmentById(id);

        appointment.setStatus(AppointmentStatus.COMPLETED);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment cancelAppointment(Long id) {

        Appointment appointment = getAppointmentById(id);

        appointment.setStatus(AppointmentStatus.CANCELLED);

        return appointmentRepository.save(appointment);
    }
}