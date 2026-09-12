package com.pashusetu.pashusetu.repository;

import com.pashusetu.pashusetu.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    List<Farmer> findByVillageTalukaDistrictId(Long districtId);
}