package com.pashusetu.pashusetu.repository;

import com.pashusetu.pashusetu.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepository extends JpaRepository<Breed, Long> {
}