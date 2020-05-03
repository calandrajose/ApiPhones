package com.phones.phones.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String dni;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private Date creationDate;

    @NotNull
    private boolean isActive;

    // Muchos USUARIOS tienen una CIUDAD
    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name="id_city", nullable = false)
    private City city;

    /*
    @NotNull
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_x_user_rol",
            joinColumns = { @JoinColumn(name = "id_user") },
            inverseJoinColumns = { @JoinColumn(name = "id_user_rol") }
    )
    private Set<UserRol> projects = new HashSet<>();
    */

}
