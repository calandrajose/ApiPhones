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
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    // Muchas CIUDADES tiene una PROVINCIA
    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name="id_province", nullable = false)
    private Province province;

    // Una CIUDAD tiene muchos USUARIOS
    @JsonBackReference
    @OneToMany(mappedBy = "city")
    private List<User> users;

}
