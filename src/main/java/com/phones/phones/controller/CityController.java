package com.phones.phones.controller;

import com.phones.phones.exception.city.CityAlreadyExistException;
import com.phones.phones.exception.city.CityNotExistException;
import com.phones.phones.model.City;
import com.phones.phones.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(final CityService cityService) {
        this.cityService = cityService;
    }


    //@PostMapping("/")
    public void createCity(@RequestBody @Valid final City city) {
        try {
            cityService.add(city);
        } catch (CityAlreadyExistException e) {
            e.printStackTrace();
        }
    }

    //@GetMapping("/")
    public List<City> getAllCities() {
        return cityService.getAll();
    }

    //@GetMapping("/{id}")
    public Optional<City> getCityById(@PathVariable final Long id) throws CityNotExistException {
        return cityService.getById(id);
    }

}
