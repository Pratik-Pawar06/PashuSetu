package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.entity.Veterinarian;

import java.util.List;

public interface VeterinarianService {

    Veterinarian addVeterinarian(Veterinarian veterinarian);

    List<Veterinarian> getAllVeterinarians();

    Veterinarian getVeterinarianById(Long id);

    Veterinarian getVeterinarianByLicenseNumber(String licenseNumber);

    Veterinarian getVeterinarianByUserId(Long userId);

    List<Veterinarian> getVeterinariansByDistrictId(Long districtId);

    List<Veterinarian> getVeterinariansByTalukaId(Long talukaId);

    List<Veterinarian> getAvailableVeterinarians();

    Veterinarian updateVeterinarian(Long id, Veterinarian veterinarian);

    void deleteVeterinarian(Long id);
}