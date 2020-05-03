package com.phones.phones.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String prefix;

    // Muchas CIUDADES tienen una PROVINCIA
    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name="id_province", nullable = false)
    private Province province;

    // Una CIUDAD tiene muchos USUARIOS
    // No se serializa
    @JsonBackReference
    @OneToMany(mappedBy = "city")
    private List<User> users;

    // Una ciudad tiene muchas tarifas
    @JsonBackReference
    @OneToMany(mappedBy = "originCity")
    private List<Rate> originRates;

    @JsonBackReference
    @OneToMany(mappedBy = "destinationCity")
    private List<Rate> destinationRates;

}
