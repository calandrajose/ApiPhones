package com.phones.phones.dto;

import com.phones.phones.model.UserRole;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserDto {

    @Id
    private Long id;
    private String name;
    private String surname;
    private String dni;
    private String username;
    private String city;
    private String province;
    //private List<UserRole> roles = new ArrayList<>();

}
