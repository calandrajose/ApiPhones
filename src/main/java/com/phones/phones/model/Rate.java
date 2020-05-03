package com.phones.phones.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Float priceMinute;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "id_city_origin")
    private City originCity;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "id_city_destination")
    private City destinationCity;

}
