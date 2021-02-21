package com.vehicle.entity;


import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "DEALER")
public class Dealer {

    @Column(name = "DEALER_ID")
    @NotNull
    private int dealer_id;
    @OneToMany(fetch = FetchType.LAZY)
    private Vehicle vehicle;
}
