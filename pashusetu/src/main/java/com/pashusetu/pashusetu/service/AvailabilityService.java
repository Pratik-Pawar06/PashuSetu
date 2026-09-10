package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.entity.Availability;

import java.util.List;

public interface AvailabilityService {

    Availability createAvailability(Availability availability);

    List<Availability> getAllAvailability();

    List<Availability> getAvailabilityByVeterinarian(Long veterinarianId);

    Availability getAvailabilityById(Long id);

    Availability updateAvailability(Long id, Availability availability);

    void deleteAvailability(Long id);
}