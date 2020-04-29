package com.phones.phones.model;

import java.util.Date;

public class Invoice {

    private Long id;

    // la linea ya tiene al cliente...
    private Line line;

    private Integer numberCalls;
    private Float costPrice;
    private Float totalPrice;
    private Date creationDate;
    private Date dueDate;


}
