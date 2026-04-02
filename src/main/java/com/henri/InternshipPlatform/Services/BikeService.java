package com.henri.InternshipPlatform.Services;

import com.henri.InternshipPlatform.Models.Bike;
import com.henri.InternshipPlatform.Repositories.BikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {
    private final BikeRepository bikeRepository;

    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public Bike addBike(Bike bike) {
        if (bikeRepository.existsByBikeId(bike.getBikeId())) {
            throw new IllegalArgumentException("Bike ID already exists: " + bike.getBikeId());
        }
        return bikeRepository.save(bike);
    }

    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    public List<Bike> getAvailableBikes() {
        return bikeRepository.findByAvailableTrue();
    }

    public Bike getBikeById(String bikeId) {
        return bikeRepository.findByBikeId(bikeId)
                .orElseThrow(() -> new IllegalArgumentException("Bike not found: " + bikeId));
    }

    public void deleteBike(Long id) {
        bikeRepository.deleteById(id);
    }
}
