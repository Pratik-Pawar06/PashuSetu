package com.pashusetu.pashusetu.repository;

import com.pashusetu.pashusetu.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.time.DayOfWeek;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    List<Availability> findByVeterinarianId(Long veterinarianId);

    List<Availability> findByVeterinarianIdAndDayOfWeek(
            Long veterinarianId,
            DayOfWeek dayOfWeek
    );

    List<Availability> findByVeterinarianIdAndAvailableTrue(
            Long veterinarianId
    );

    Optional<Availability> findByVeterinarianIdAndDayOfWeekAndAvailableTrue(
            Long veterinarianId,
            DayOfWeek dayOfWeek
    );
}