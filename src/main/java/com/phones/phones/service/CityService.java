package com.phones.phones.service;

import com.phones.phones.exception.city.CityAlreadyExistException;
import com.phones.phones.exception.city.CityNotExistException;
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


    public City create(City newCity) throws CityAlreadyExistException {
        Optional<City> city = cityRepository.findByName(newCity.getName());
        if (city.isPresent()) {
            throw new CityAlreadyExistException();
        }
        return cityRepository.save(newCity);
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public Optional<City> findById(Long id) throws CityNotExistException {
        Optional<City> city = cityRepository.findById(id);
        if (city.isEmpty()) {
            throw new CityNotExistException();
        }
        return city;
    }

    /*
    public Optional<City> findByUserId(Long id) {
        return cityRepository.findByUserId(id);
    }
     */

}
