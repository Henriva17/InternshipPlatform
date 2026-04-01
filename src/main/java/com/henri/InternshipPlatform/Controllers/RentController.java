package com.henri.InternshipPlatform.Controllers;


import com.henri.InternshipPlatform.Models.Rent;
import com.henri.InternshipPlatform.Services.RentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rents")
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService){
        this.rentService =rentService;
    }

    // POST /api/rents/rent
    // body: { "bikeId": "B001", "customerId": "CUS1", "hours": 3 }
    @PostMapping("/rent")
    public ResponseEntity<Rent> rentBike(
            @RequestParam String bikeId,
            @RequestParam String customerId,
            @RequestParam int hours) {
        return ResponseEntity.ok(rentService.rentBike(bikeId, customerId, hours));
    }

    // PUT /api/rents/return/{bikeId}
    @PutMapping("/return/{bikeId}")
    public ResponseEntity<String> returnBike(@PathVariable String bikeId) {
        return ResponseEntity.ok(rentService.returnBike(bikeId));
    }

    // GET /api/rents
    @GetMapping
    public List<Rent> getAllActiveRentals() {
        return rentService.getAllActiveRentals();
    }

    // GET /api/rents/customer/{customerId}
    @GetMapping("/customer/{customerId}")
    public List<Rent> getRentalsByCustomer(@PathVariable String customerId) {
        return rentService.getRentalsByCustomer(customerId);
    }
}
