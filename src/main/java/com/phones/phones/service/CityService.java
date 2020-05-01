package com.phones.phones.service;

import com.phones.phones.model.City;
import com.phones.phones.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(final CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    public void add(City city) {
        cityRepository.save(city);
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public Optional<City> getById(Long id) {
        return cityRepository.findById(id);
    }

    public Optional<City> getByName(String name) {
        return cityRepository.findByName(name);
    }
}
