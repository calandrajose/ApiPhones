package com.phones.phones.controller;

import com.phones.phones.model.City;
import com.phones.phones.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(final CityService cityService) {
        this.cityService = cityService;
    }


    @PostMapping("/")
    public void add(@RequestBody @Valid final City city) {
        cityService.add(city);
    }

    @GetMapping("/")
    public List<City> getAll() {
        return cityService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<City> getById(@PathVariable final Long id) {
        return cityService.getById(id);
    }

    @GetMapping("/name/{name}")
    public Optional<City> getByName(@PathVariable final String name) {
        return cityService.getByName(name);
    }

}
