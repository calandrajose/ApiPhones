package com.phones.phones.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserLoginDto implements Serializable {

    private String username;
    private String password;

}
