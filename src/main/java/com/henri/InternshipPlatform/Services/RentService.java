package com.henri.InternshipPlatform.Services;

import com.henri.InternshipPlatform.Models.Bike;
import com.henri.InternshipPlatform.Models.Customer;
import com.henri.InternshipPlatform.Models.Rent;
import com.henri.InternshipPlatform.Repositories.BikeRepository;
import com.henri.InternshipPlatform.Repositories.CustomerRepository;
import com.henri.InternshipPlatform.Repositories.RentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RentService {
    private final RentRepository rentRepository;
    private final BikeRepository bikeRepository;
    private final CustomerRepository  customerRepository;

    public RentService(RentRepository rentRepository,
                       BikeRepository bikeRepository,
                       CustomerRepository customerRepository) {
        this.rentRepository     = rentRepository;
        this.bikeRepository     = bikeRepository;
        this.customerRepository = customerRepository;
    }

    public Rent rentBike(String bikeId, String customerId, int hours) {
        Bike bike = bikeRepository.findByBikeId(bikeId)
                .orElseThrow(() -> new IllegalArgumentException("Bike not found: " + bikeId));

        if (!bike.isAvailable()) {
            throw new IllegalStateException("Bike is not available: " + bikeId);
        }

        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));

        bike.setAvailable(false);
        bikeRepository.save(bike);

        Rent rent = new Rent(bike, customer, hours);
        return rentRepository.save(rent);
    }

    // returns a receipt summary as a string
    public String returnBike(String bikeId) {
        Bike bike = bikeRepository.findByBikeId(bikeId)
                .orElseThrow(() -> new IllegalArgumentException("Bike not found: " + bikeId));

        Rent rent = rentRepository.findByBikeAndActiveTrue(bike)
                .orElseThrow(() -> new IllegalStateException("No active rental found for bike: " + bikeId));

        bike.setAvailable(true);
        bikeRepository.save(bike);

        rent.setActive(false);
        rentRepository.save(rent);

        return String.format(
                "===== RETURN RECEIPT =====%n" +
                        "Customer   : %s%n" +
                        "Bike       : %s (%s)%n" +
                        "Hours      : %d%n" +
                        "Total Cost : $%.2f%n" +
                        "=========================",
                rent.getCustomer().getName(),
                rent.getBike().getBikeModel(),
                rent.getBike().getBikeType(),
                rent.getHours(),
                rent.getTotalPrice()
        );
    }

    public List<Rent> getAllActiveRentals() {
        return rentRepository.findByActiveTrue();
    }

    public List<Rent> getRentalsByCustomer(String customerId) {
        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
        return rentRepository.findByCustomer(customer);
    }
}
