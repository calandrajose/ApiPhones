package com.phones.phones.model;

import java.util.Date;

public class Line {

    private Long id;
    private Integer number;
    private Date creationDate;
    private boolean isActive;

    // Relaciones DB
    private Client client;

}
