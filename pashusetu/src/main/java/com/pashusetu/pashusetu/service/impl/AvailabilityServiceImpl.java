package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.entity.Availability;
import com.pashusetu.pashusetu.repository.AvailabilityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public Availability createAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    @Override
    public List<Availability> getAllAvailability() {
        return availabilityRepository.findAll();
    }

    @Override
    public List<Availability> getAvailabilityByVeterinarian(Long veterinarianId) {
        return availabilityRepository.findByVeterinarianId(veterinarianId);
    }

    @Override
    public Availability getAvailabilityById(Long id) {
        return availabilityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Availability not found"
                ));
    }

    @Override
    public Availability updateAvailability(Long id, Availability availability) {

        Availability existing = getAvailabilityById(id);

        existing.setVeterinarian(availability.getVeterinarian());
        existing.setDayOfWeek(availability.getDayOfWeek());
        existing.setStartTime(availability.getStartTime());
        existing.setEndTime(availability.getEndTime());
        existing.setAvailable(availability.getAvailable());

        return availabilityRepository.save(existing);
    }

    @Override
    public void deleteAvailability(Long id) {
        Availability existing = getAvailabilityById(id);
        availabilityRepository.delete(existing);
    }
}