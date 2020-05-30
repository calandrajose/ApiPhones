package com.phones.phones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfrastructureCallDto {

    private String user;
    private String password;
    private String originNumber;
    private String destinationNumber;
    private Integer duration;
    private Date creationDate;

}
