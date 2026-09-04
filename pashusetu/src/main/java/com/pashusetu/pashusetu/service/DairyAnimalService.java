package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.dto.DairyAnimalRequest;
import com.pashusetu.pashusetu.entity.DairyAnimal;
import com.pashusetu.pashusetu.dto.DairyAnimalUpdateRequest;

import java.util.List;

public interface DairyAnimalService {

    DairyAnimal registerAnimal(DairyAnimalRequest request);

    List<DairyAnimal> getAllAnimals();

    DairyAnimal getAnimalById(Long id);

    DairyAnimal getAnimalByTagNumber(String tagNumber);

    DairyAnimal updateAnimal(Long id, DairyAnimalUpdateRequest request);

    void deleteAnimal(Long id);
}