package com.phones.phones.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class City {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    // Relacion DB
    @ManyToOne()
    @JoinColumn(name="fk_province", nullable = false)
    private Province province;
}
