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

    /* Url's a consumir */
    private static final String url = "http://localhost:8080/api/backoffice/users/";
    private final String urlRandomPersons = "https://randomuser.me/api/";


    @PostConstruct
    private void init() {
        rest = new RestTemplateBuilder()
                .build();
    }

    public List<User> getUserFromApi() {
        return (List<User>) rest.getForObject(url, User.class);
    }

    public User addUserFromApi(User user) {
        // Poner url necesaria...
        return rest.postForObject(url, user, User.class);
    }

    public void getUsersFromRandomApi() {
        String json = rest.getForObject(urlRandomPersons, String.class);
        System.out.println(json);
    }

}
