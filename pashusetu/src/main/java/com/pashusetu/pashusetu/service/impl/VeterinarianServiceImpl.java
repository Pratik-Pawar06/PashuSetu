package com.pashusetu.pashusetu.service.impl;

import com.pashusetu.pashusetu.entity.District;
import com.pashusetu.pashusetu.entity.Taluka;
import com.pashusetu.pashusetu.entity.User;
import com.pashusetu.pashusetu.entity.Veterinarian;
import com.pashusetu.pashusetu.repository.DistrictRepository;
import com.pashusetu.pashusetu.repository.TalukaRepository;
import com.pashusetu.pashusetu.repository.UserRepository;
import com.pashusetu.pashusetu.repository.VeterinarianRepository;
import com.pashusetu.pashusetu.service.VeterinarianService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarianServiceImpl implements VeterinarianService {

    private final VeterinarianRepository veterinarianRepository;
    private final UserRepository userRepository;
    private final DistrictRepository districtRepository;
    private final TalukaRepository talukaRepository;

    public VeterinarianServiceImpl(
            VeterinarianRepository veterinarianRepository,
            UserRepository userRepository,
            DistrictRepository districtRepository,
            TalukaRepository talukaRepository) {

        this.veterinarianRepository = veterinarianRepository;
        this.userRepository = userRepository;
        this.districtRepository = districtRepository;
        this.talukaRepository = talukaRepository;
    }

    @Override
    public Veterinarian addVeterinarian(Veterinarian veterinarian) {

        User user = userRepository
                .findById(veterinarian.getUser().getId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with id: "
                                        + veterinarian.getUser().getId()
                        )
                );

        District district = districtRepository
                .findById(veterinarian.getDistrict().getId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "District not found with id: "
                                        + veterinarian.getDistrict().getId()
                        )
                );

        Taluka taluka = talukaRepository
                .findById(veterinarian.getTaluka().getId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Taluka not found with id: "
                                        + veterinarian.getTaluka().getId()
                        )
                );

        veterinarian.setUser(user);
        veterinarian.setDistrict(district);
        veterinarian.setTaluka(taluka);

        return veterinarianRepository.save(veterinarian);
    }

    @Override
    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianRepository.findAll();
    }

    @Override
    public Veterinarian getVeterinarianById(Long id) {
        return veterinarianRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Veterinarian not found with id: " + id
                        )
                );
    }

    @Override
    public Veterinarian getVeterinarianByLicenseNumber(
            String licenseNumber) {

        return veterinarianRepository
                .findByLicenseNumber(licenseNumber)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Veterinarian not found with license number: "
                                        + licenseNumber
                        )
                );
    }

    @Override
    public Veterinarian getVeterinarianByUserId(Long userId) {

        return veterinarianRepository
                .findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Veterinarian not found for user id: "
                                        + userId
                        )
                );
    }

    @Override
    public List<Veterinarian> getVeterinariansByDistrictId(
            Long districtId) {

        districtRepository.findById(districtId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "District not found with id: "
                                        + districtId
                        )
                );

        return veterinarianRepository.findByDistrictId(districtId);
    }

    @Override
    public List<Veterinarian> getVeterinariansByTalukaId(
            Long talukaId) {

        talukaRepository.findById(talukaId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Taluka not found with id: "
                                        + talukaId
                        )
                );

        return veterinarianRepository.findByTalukaId(talukaId);
    }

    @Override
    public List<Veterinarian> getAvailableVeterinarians() {
        return veterinarianRepository.findByAvailableTrue();
    }

    @Override
    public Veterinarian updateVeterinarian(
            Long id,
            Veterinarian veterinarian) {

        Veterinarian existingVeterinarian =
                getVeterinarianById(id);

        User user = userRepository
                .findById(veterinarian.getUser().getId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with id: "
                                        + veterinarian.getUser().getId()
                        )
                );

        District district = districtRepository
                .findById(veterinarian.getDistrict().getId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "District not found with id: "
                                        + veterinarian.getDistrict().getId()
                        )
                );

        Taluka taluka = talukaRepository
                .findById(veterinarian.getTaluka().getId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Taluka not found with id: "
                                        + veterinarian.getTaluka().getId()
                        )
                );

        existingVeterinarian.setUser(user);
        existingVeterinarian.setDistrict(district);
        existingVeterinarian.setTaluka(taluka);
        existingVeterinarian.setQualification(
                veterinarian.getQualification()
        );
        existingVeterinarian.setLicenseNumber(
                veterinarian.getLicenseNumber()
        );
        existingVeterinarian.setSpecialization(
                veterinarian.getSpecialization()
        );
        existingVeterinarian.setAvailable(
                veterinarian.isAvailable()
        );

        return veterinarianRepository.save(existingVeterinarian);
    }

    @Override
    public void deleteVeterinarian(Long id) {

        Veterinarian veterinarian =
                getVeterinarianById(id);

        veterinarianRepository.delete(veterinarian);
    }
}