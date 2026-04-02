package com.henri.InternshipPlatform.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String customerId;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits including the (0)")
    private String phone;

    public Customer(){}

    public  Customer(String customerId, String name, String email, String phone ){
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId()           { return id; }
    public String getCustomerId() { return customerId; }
    public String getName()       { return name; }
    public String getEmail()      { return email; }
    public String getPhone()      { return phone; }

    public void setCustomerId(String customerId) { this.customerId = customerId; }



}
