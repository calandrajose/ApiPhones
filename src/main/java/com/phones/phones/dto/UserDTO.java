package com.phones.phones.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String dni;
    private String username;
    //private String password;
    private Boolean isActive;
    private String city;
    private String province;

}
