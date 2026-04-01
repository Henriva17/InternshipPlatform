package com.henri.InternshipPlatform.Repositories;

import com.henri.InternshipPlatform.Models.Bike;
import com.henri.InternshipPlatform.Models.Customer;
import com.henri.InternshipPlatform.Models.Rent;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByActiveTrue();
    List<Rent> findByCustomer(Customer customer);
    Optional<Rent> findByBikeAndActiveTrue(Bike bike);
}
