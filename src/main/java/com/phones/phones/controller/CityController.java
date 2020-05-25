package com.phones.phones.controller;

import com.phones.phones.exception.city.CityAlreadyExistException;
import com.phones.phones.exception.city.CityNotExistException;
import com.phones.phones.exception.user.UserSessionNotExistException;
import com.phones.phones.model.City;
import com.phones.phones.model.User;
import com.phones.phones.service.CityService;
import com.phones.phones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;
    private final SessionManager sessionManager;

    @Autowired
    public CityController(final CityService cityService, final SessionManager sessionManager) {
        this.cityService = cityService;
        this.sessionManager = sessionManager;
    }


    @PostMapping("/")
    public ResponseEntity createCity(@RequestHeader("Authorization") String sessionToken,
                                     @RequestBody @Valid final City city) throws CityAlreadyExistException, UserSessionNotExistException {
        User currentUser = sessionManager.getCurrentUser(sessionToken);
        if (currentUser.hasRoleEmployee()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(cityService.create(city));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<City>> findAllCities(@RequestHeader("Authorization") final String sessionToken) throws UserSessionNotExistException {
        User currentUser = sessionManager.getCurrentUser(sessionToken);
        if (currentUser.hasRoleEmployee()) {
            List<City> cities = cityService.findAll();
            return (cities.size() > 0) ? ResponseEntity.ok(cities) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<City>> findCityById(@RequestHeader("Authorization") String sessionToken,
                                      @PathVariable final Long id) throws CityNotExistException, UserSessionNotExistException {
        User currentUser = sessionManager.getCurrentUser(sessionToken);
        if (currentUser.hasRoleEmployee()) {
            return ResponseEntity.ok(cityService.findById(id));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
