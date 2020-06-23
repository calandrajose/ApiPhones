package com.phones.phones.service.integration;

import com.phones.phones.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Component
public class IntegrationComponent {

    RestTemplate rest;
    private static String url = "httt://localhost:8080/api/backoffice/users/";  /*url a consumir***/


    @PostConstruct
    private void init(){
        rest = new RestTemplateBuilder()
                .build();
    }

    public List<User> getUserFromApi(){
        return (List<User>) rest.getForObject(url, User.class);
    }

}
