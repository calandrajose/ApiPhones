package com.phones.phones.model;

import java.util.Date;

public class Call {

    private Long id;
    private Integer duration;
    private Float totalPrice;
    private Date creationDate;

    //Relacion DB
    private Line originLine;
    private Line destinationLine;

    //Factura
    //private Invoice invoice;

}
