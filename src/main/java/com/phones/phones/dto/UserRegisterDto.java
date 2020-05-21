package com.phones.phones.dto;

import com.phones.phones.model.City;

import java.io.Serializable;

public class UserRegisterDto implements Serializable {

    private String name;
    private String surname;
    private String dni;
    private String username;
    private String password;
    private City city;

}
