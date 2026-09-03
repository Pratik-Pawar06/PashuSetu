package com.pashusetu.pashusetu.repository;

import com.pashusetu.pashusetu.entity.DairyAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DairyAnimalRepository extends JpaRepository<DairyAnimal, Long> {

    Optional<DairyAnimal> findByTagNumber(String tagNumber);

    boolean existsByTagNumber(String tagNumber);
}