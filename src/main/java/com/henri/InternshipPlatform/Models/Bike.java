package com.henri.InternshipPlatform.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.aspectj.bridge.Message;

@Entity
@Table(name = "bikes")
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message =" Bike is requuired")        // spring boot starter validation als dependency
    @Column(unique = true, nullable = false)
    private String bikeId;

    @NotBlank(message = "Bike Model is required")
    private String bikeModel;

    @NotBlank(message = "Bike Type is required")
    private String bikeType;

    @Positive(message = "price must be greater than 0")
    private double pricePerHour;

    private boolean available = true;

    public Bike(){}

    public Bike(String bikeId, String bikeModel, String bikeType, double pricePerHour){
        this.bikeId = bikeId;
        this.bikeModel = bikeModel;
        this.bikeType = bikeType;
        this.pricePerHour = pricePerHour;
        this.available = true;
    }
    public Long getId()              { return id; }
    public String getBikeId()        { return bikeId; }
    public String getBikeModel()     { return bikeModel; }
    public String getBikeType()      { return bikeType; }
    public double getPricePerHour()  { return pricePerHour; }
    public boolean isAvailable()     { return available; }

    public void setAvailable(boolean available) { this.available = available; }

    public double calculatePrice(int hours) {
        return pricePerHour * hours;
    }



}
