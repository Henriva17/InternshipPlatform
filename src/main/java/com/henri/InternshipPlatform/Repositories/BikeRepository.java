package com.henri.InternshipPlatform.Repositories;

import com.henri.InternshipPlatform.Models.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

    @Repository
    public interface BikeRepository extends JpaRepository<Bike, Long> {
        List<Bike> findByAvailableTrue();
        Optional<Bike> findByBikeId(String bikeId);
        boolean existsByBikeId(String bikeId);
    }

