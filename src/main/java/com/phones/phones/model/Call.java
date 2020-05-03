package com.phones.phones.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "call")
public class Call {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer duration;

    @NotNull
    private Float totalPrice;

    @NotNull
    private Date creationDate;


    //Relaciones DB
/*
    //Tarifa
    @NotNull
    private Rate rate;
    //Factura
    @NotNull
    private Invoice invoice;
 */

    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name="id_origin_line", nullable = false)
    private Line originline;

    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name="id_destination_line", nullable = false)
    private Line destinationline;

}
