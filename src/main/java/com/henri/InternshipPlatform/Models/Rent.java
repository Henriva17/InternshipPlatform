package com.henri.InternshipPlatform.Models;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bike_id", nullable = false)
    private Bike bike;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    private int hours;
    private double totalPrice;
    private LocalDateTime rentedAt;
    private boolean active = true;  // false once bike is returned

    public Rent() {}

    public Rent(Bike bike, Customer customer, int hours) {
        this.bike       = bike;
        this.customer   = customer;
        this.hours      = hours;
        this.totalPrice = bike.calculatePrice(hours);
        this.rentedAt   = LocalDateTime.now();
        this.active     = true;
    }

    public Long getId()           { return id; }
    public Bike getBike()         { return bike; }
    public Customer getCustomer() { return customer; }
    public int getHours()         { return hours; }
    public double getTotalPrice() { return totalPrice; }
    public LocalDateTime getRentedAt() { return rentedAt; }
    public boolean isActive()     { return active; }

    public void setActive(boolean active) { this.active = active; }
}