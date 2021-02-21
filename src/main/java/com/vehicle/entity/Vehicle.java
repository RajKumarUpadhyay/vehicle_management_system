package com.vehicle.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "VEHICLE")
public class Vehicle {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(name = "CODE")
    @NonNull
    private String code;
    @NotNull
    @Column(name = "MAKE")
    private String make;
    @NotNull
    @Column(name = "MODEL")
    private String model;
    @Column(name = "KW")
    private int KW;
    @Column(name = "YEAR")
    private String year;
    @Column(name = "COLOR")
    private String color;
    @Column(name = "PRICE" +
            "")
    private double price;
}
