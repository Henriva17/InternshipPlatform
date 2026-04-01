package com.henri.InternshipPlatform.Controllers;

import com.henri.InternshipPlatform.Models.Bike;
import com.henri.InternshipPlatform.Services.BikeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bikes")
public class BikeController {
    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    // GET /api/bikes
    @GetMapping
    public List<Bike> getAllBikes() {
        return bikeService.getAllBikes();
    }

    // GET /api/bikes/available
    @GetMapping("/available")
    public List<Bike> getAvailableBikes() {
        return bikeService.getAvailableBikes();
    }

    // GET /api/bikes/{bikeId}
    @GetMapping("/{bikeId}")
    public Bike getBikeById(@PathVariable String bikeId) {
        return bikeService.getBikeById(bikeId);
    }

    // POST /api/bikes
    @PostMapping
    public ResponseEntity<Bike> addBike(@Valid @RequestBody Bike bike) {
        return ResponseEntity.ok(bikeService.addBike(bike));
    }

    // DELETE /api/bikes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBike(@PathVariable Long id) {
        bikeService.deleteBike(id);
        return ResponseEntity.ok("Bike deleted.");
    }
}
