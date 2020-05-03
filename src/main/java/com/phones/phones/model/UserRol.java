package com.phones.phones.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class UserRol {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String rol;

/*
    @ManyToMany(mappedBy = "user")
    private Set<User> employees = new HashSet<>();

 */

}
