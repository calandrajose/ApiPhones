package com.phones.phones;

import com.phones.phones.model.City;
import com.phones.phones.model.User;

import java.util.ArrayList;
import java.util.List;

public class TestObjects {


public static User testUser(){
    User newUser = User.builder()
            .id(1L)
            .name("Rodrigo")
            .surname("Leon")
            .dni("404040")
            .username("rl")
            .password("123")
            .isActive(true)
            .city(new City())
            .build();
    return newUser;
}

    public static User testDisabledUser(){
        User newUser = User.builder()
                .id(1L)
                .name("Rodrigo")
                .surname("Leon")
                .dni("404040")
                .username("rl")
                .password("123")
                .isActive(false)
                .city(new City())
                .build();
        return newUser;
    }

    public static List<User> testListOfUsers(){
        List<User> users = new ArrayList<>();
        User newUser = User.builder()
                .id(1L)
                .name("Rodrigo")
                .surname("Leon")
                .dni("404040")
                .username("rl")
                .password("123")
                .isActive(true)
                .city(new City())
                .build();

        User newUser2 = User.builder()
                .id(2L)
                .name("Jose")
                .surname("Calandra")
                .dni("373737")
                .username("jc")
                .password("123")
                .isActive(true)
                .city(new City())
                .build();
        users.add(newUser);
        users.add(newUser2);

        return users;
    }

}
