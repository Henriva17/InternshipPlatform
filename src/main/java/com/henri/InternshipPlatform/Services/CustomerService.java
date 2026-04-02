package com.henri.InternshipPlatform.Services;

import com.henri.InternshipPlatform.Models.Customer;
import com.henri.InternshipPlatform.Repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class CustomerService {

        private final CustomerRepository customerRepository;

        public CustomerService(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        public Customer registerCustomer(Customer customer) {
            if (customerRepository.existsByEmail(customer.getEmail())) {
                throw new IllegalArgumentException("Email already registered: " + customer.getEmail());
            }
            // auto-generate customer ID
            long count = customerRepository.count();
            customer.setCustomerId("CUS" + (count + 1));
            return customerRepository.save(customer);
        }

        public List<Customer> getAllCustomers() {
            return customerRepository.findAll();
        }

        public Customer getCustomerById(String customerId) {
            return customerRepository.findByCustomerId(customerId)
                    .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
        }
        public void deleteCustomer(Long id){
            customerRepository.deleteById(id);
        }

    }
