package com.phones.phones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RateDto {

    @Id
    private Long id;

    private String originCity;

    private String destinationCity;

    private Float priceMinute;

}
