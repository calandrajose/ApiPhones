package com.phones.phones.model;

public class Client extends User {

    private Long id;

    // Relaciones DB
    private User user;
    private ClientType clientType;

}
