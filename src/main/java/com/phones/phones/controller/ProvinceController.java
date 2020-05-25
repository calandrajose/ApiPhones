package com.phones.phones.controller;

import com.phones.phones.exception.province.ProviceAlreadyExistException;
import com.phones.phones.exception.province.ProvinceNotExistException;
import com.phones.phones.model.Province;
import com.phones.phones.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping("/api/provinces")
public class ProvinceController {

    private final ProvinceService provinceService;

    @Autowired
    public ProvinceController(final ProvinceService provinceService) {
        this.provinceService = provinceService;
    }


    //@PostMapping("/")
    public void createProvince(@RequestBody @Valid final Province province) throws ProviceAlreadyExistException {
        provinceService.add(province);
    }

    //@GetMapping("/")
    public List<Province> getAllProvinces() {
        return provinceService.getAll();
    }

    //@GetMapping("/{id}")
    public Optional<Province> getProvinceById(@PathVariable final Long id) throws ProvinceNotExistException {
        return provinceService.getById(id);
    }

}
