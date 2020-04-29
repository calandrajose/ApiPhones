package com.phones.phones.model;

import java.util.Date;

public abstract class User {

    private Long id;
    private String name;
    private String surname;
    private String dni;
    private Date creationDate;
    private boolean isActive;

    //Ciudad - relacion DB
    //private City city;

}
