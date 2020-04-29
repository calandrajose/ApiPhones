package com.phones.phones.model;

public class Employee extends User {

    private Long id;
    private String username;
    private String password;

    // Relacion bd
    private User user;

}
