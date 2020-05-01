package com.phones.phones.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Call {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Integer duration;

    @NotNull
    private Float totalPrice;

    @NotNull
    private Date creationDate;

    //Relaciones DB
    @NotNull
    private Line originLine;

    @NotNull
    private Line destinationLine;

    //Tarifa
    @NotNull
    private Rate rate;

    //Factura
    @NotNull
    private Invoice invoice;


}
