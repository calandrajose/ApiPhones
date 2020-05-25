package com.phones.phones.dto;

import com.phones.phones.model.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto implements Serializable {

    private String name;
    private String surname;
    private String dni;
    private String username;
    private String password;
    private City city;

}
