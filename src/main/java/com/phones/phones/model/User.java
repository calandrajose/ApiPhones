package com.phones.phones.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @NotNull
    @JsonManagedReference
    @ManyToOne()
    @JoinColumn(name="id_city", nullable = false)
    private City city;

    @NotNull
    @JsonManagedReference
    @ManyToMany()
    @JoinTable(
            name = "user_x_user_rol",
            joinColumns = @JoinColumn(name = "id_user") ,
            inverseJoinColumns = @JoinColumn(name = "id_user_rol")
    )
    private List<UserRole> userRoles = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "userLine")
    private List<Line> lines;

}
