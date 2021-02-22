package com.vehicle.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "VEHICLE")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    public Vehicle(String make, String model, int KW, int year, String color, double price) {
        this.make = make;
        this.model = model;
        this.KW = KW;
        this.year = year;
        this.color = color;
        this.price = price;
    }

    @Column(name = "MAKE")
    private String make;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "KW")
    private int KW;

    @Column(name = "YEAR")
    private int year;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "PRICE")
    private double price;
}
