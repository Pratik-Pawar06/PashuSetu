package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.entity.Veterinarian;
import com.pashusetu.pashusetu.entity.VeterinarianType;
import java.util.List;

public interface VeterinarianService {

    Veterinarian addVeterinarian(Veterinarian veterinarian);

    List<Veterinarian> getAllVeterinarians();

    Veterinarian getVeterinarianById(Long id);

    Veterinarian getVeterinarianByLicenseNumber(
            String licenseNumber
    );

    Veterinarian getVeterinarianByUserId(
            Long userId
    );

    List<Veterinarian> getVeterinariansByDistrictId(
            Long districtId
    );

    List<Veterinarian> getVeterinariansByTalukaId(
            Long talukaId
    );

    List<Veterinarian> getAvailableVeterinarians();

    // New methods

    List<Veterinarian> getApprovedVeterinarians();

    List<Veterinarian> getApprovedVeterinariansByTaluka(
            Long talukaId
    );

    List<Veterinarian> getApprovedVeterinariansByTalukaAndType(
            Long talukaId,
            VeterinarianType veterinarianType
    );

    List<Veterinarian> getAvailableApprovedVeterinariansByTaluka(
            Long talukaId
    );

    Veterinarian updateVeterinarian(
            Long id,
            Veterinarian veterinarian
    );

    void approveVeterinarian(Long id);

    void rejectVeterinarian(Long id);

    void deleteVeterinarian(Long id);
}