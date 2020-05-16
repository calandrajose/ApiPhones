package com.phones.phones.controller;

import com.phones.phones.model.Province;
import com.phones.phones.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {

    private final ProvinceService provinceService;

    @Autowired
    public ProvinceController(ProvinceService provinceService) {
        this.provinceService = provinceService;
    }


    @PostMapping("/")
    public void add(@RequestBody @Valid final Province province) {
        provinceService.add(province);
    }

    @GetMapping("/")
    public List<Province> getAll() {
        return provinceService.getAll();
    }

}
