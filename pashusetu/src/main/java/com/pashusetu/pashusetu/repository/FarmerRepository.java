package com.pashusetu.pashusetu.repository;

import com.pashusetu.pashusetu.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
}