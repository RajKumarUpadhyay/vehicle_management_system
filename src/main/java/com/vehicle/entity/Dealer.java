package com.vehicle.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "DEALER")
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column(name = "DEALER_ID")
    private long dealerId;
    @OneToOne
    private Vehicle vehicle;
    @Column(name = "CODE")
    private String code;

    public Dealer(long dealerId, Vehicle vehicle, String code) {
        this.dealerId = dealerId;
        this.vehicle = vehicle;
        this.code = code;
    }
}
