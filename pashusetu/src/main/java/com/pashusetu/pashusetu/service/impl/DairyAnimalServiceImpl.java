package com.pashusetu.pashusetu.service.impl;
import com.pashusetu.pashusetu.dto.DairyAnimalRequest;
import com.pashusetu.pashusetu.entity.Breed;
import com.pashusetu.pashusetu.entity.Farmer;
import com.pashusetu.pashusetu.repository.BreedRepository;
import com.pashusetu.pashusetu.repository.FarmerRepository;
import com.pashusetu.pashusetu.exception.DuplicateTagException;
import com.pashusetu.pashusetu.exception.AnimalNotFoundException;
import com.pashusetu.pashusetu.entity.DairyAnimal;
import com.pashusetu.pashusetu.repository.DairyAnimalRepository;
import com.pashusetu.pashusetu.service.DairyAnimalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DairyAnimalServiceImpl implements DairyAnimalService {

    private final DairyAnimalRepository dairyAnimalRepository;
    private final BreedRepository breedRepository;
    private final FarmerRepository farmerRepository;

    public DairyAnimalServiceImpl(
            DairyAnimalRepository dairyAnimalRepository,
            BreedRepository breedRepository,
            FarmerRepository farmerRepository) {

        this.dairyAnimalRepository = dairyAnimalRepository;
        this.breedRepository = breedRepository;
        this.farmerRepository = farmerRepository;
    }

    @Override
    public DairyAnimal registerAnimal(DairyAnimalRequest request) {

        // 1. Check whether tag number already exists
        if (dairyAnimalRepository.existsByTagNumber(
                request.getTagNumber())) {

            throw new DuplicateTagException(
                    "Animal with tag number "
                            + request.getTagNumber()
                            + " already exists"
            );
        }

        // 2. Find Breed using breedId
        Breed breed = breedRepository.findById(
                request.getBreedId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Breed not found with id: "
                                + request.getBreedId()
                )
        );

        // 3. Find Farmer using farmerId
        Farmer farmer = farmerRepository.findById(
                request.getFarmerId()
        ).orElseThrow(() ->
                new RuntimeException(
                        "Farmer not found with id: "
                                + request.getFarmerId()
                )
        );

        // 4. Create DairyAnimal entity
        DairyAnimal animal = new DairyAnimal();

        animal.setTagNumber(request.getTagNumber());
        animal.setName(request.getName());
        animal.setAnimalType(request.getAnimalType());
        animal.setBreed(breed);
        animal.setDateOfBirth(request.getDateOfBirth());
        animal.setWeight(request.getWeight());
        animal.setColor(request.getColor());
        animal.setHealthStatus(request.getHealthStatus());
        animal.setFarmer(farmer);

        // 5. Save animal in database
        return dairyAnimalRepository.save(animal);
    }

    @Override
    public List<DairyAnimal> getAllAnimals() {

        return dairyAnimalRepository.findAll();
    }

    @Override
    public DairyAnimal getAnimalById(Long id) {

        return dairyAnimalRepository.findById(id)
                .orElseThrow(() ->
                        new AnimalNotFoundException(
                                "Animal not found with id: " + id
                        )
                );
    }

    @Override
    public DairyAnimal getAnimalByTagNumber(
            String tagNumber) {

        return dairyAnimalRepository
                .findByTagNumber(tagNumber)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Animal not found with tag number: "
                                        + tagNumber
                        )
                );
    }

    @Override
    public DairyAnimal updateAnimal(
            Long id,
            DairyAnimal animal) {

        DairyAnimal existingAnimal =
                getAnimalById(id);

        existingAnimal.setTagNumber(
                animal.getTagNumber()
        );

        existingAnimal.setName(
                animal.getName()
        );

        existingAnimal.setAnimalType(
                animal.getAnimalType()
        );

        existingAnimal.setBreed(
                animal.getBreed()
        );

        existingAnimal.setDateOfBirth(
                animal.getDateOfBirth()
        );

        existingAnimal.setWeight(
                animal.getWeight()
        );

        existingAnimal.setColor(
                animal.getColor()
        );

        existingAnimal.setHealthStatus(
                animal.getHealthStatus()
        );

        existingAnimal.setFarmer(
                animal.getFarmer()
        );

        return dairyAnimalRepository.save(
                existingAnimal
        );
    }

    @Override
    public void deleteAnimal(Long id) {

        DairyAnimal animal =
                getAnimalById(id);

        dairyAnimalRepository.delete(animal);
    }
}