package com.phones.phones.service;

import com.phones.phones.model.Province;
import com.phones.phones.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {

    private final ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceService(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    public void add(Province province) {
        provinceRepository.save(province);
    }

    public List<Province> getAll() {
        return provinceRepository.findAll();
    }

}
