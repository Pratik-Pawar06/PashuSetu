package com.pashusetu.pashusetu.service;

import com.pashusetu.pashusetu.dto.DairyAnimalRequest;
import com.pashusetu.pashusetu.entity.DairyAnimal;

import java.util.List;

public interface DairyAnimalService {

    DairyAnimal registerAnimal(DairyAnimalRequest request);

    List<DairyAnimal> getAllAnimals();

    DairyAnimal getAnimalById(Long id);

    DairyAnimal getAnimalByTagNumber(String tagNumber);

    DairyAnimal updateAnimal(Long id, DairyAnimal animal);

    void deleteAnimal(Long id);
}