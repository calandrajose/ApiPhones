package com.phones.phones.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Rate {

    @Id
    @GeneratedValue
    private Long id;

    //Relacion DB
    @NotNull
    private City originCity;

    @NotNull
    private City destinationCity;


    @NotNull
    private Float priceMinute;

}
