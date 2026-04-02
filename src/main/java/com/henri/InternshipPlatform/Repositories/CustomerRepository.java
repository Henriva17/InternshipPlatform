package com.henri.InternshipPlatform.Repositories;

import com.henri.InternshipPlatform.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);
    Optional<Customer> findByCustomerId(String customerId);
}
