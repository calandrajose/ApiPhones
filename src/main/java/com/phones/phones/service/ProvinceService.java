package com.phones.phones.service;

import com.phones.phones.exception.province.ProviceAlreadyExistException;
import com.phones.phones.exception.province.ProvinceNotExistException;
import com.phones.phones.model.Province;
import com.phones.phones.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceService(final ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }


    public void add(Province newProvince) throws ProviceAlreadyExistException {
        Optional<Province> province = provinceRepository.findByName(newProvince.getName());
        if (province.isPresent()) {
            throw new ProviceAlreadyExistException();
        }
        provinceRepository.save(newProvince);
    }

    public List<Province> getAll() {
        return provinceRepository.findAll();
    }

    public Optional<Province> getById(Long id) throws ProvinceNotExistException {
        Optional<Province> province = provinceRepository.findById(id);
        if (province.isEmpty()) {
            throw new ProvinceNotExistException();
        }
        return province;
    }

    public Optional<Province> getByUserId(Long id) {
        return provinceRepository.findByUserId(id);
    }

}
