package com.phones.phones.controller;

import com.phones.phones.service.ProviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provinces")
public class ProvinceController {

    private final ProviceService proviceService;

    @Autowired
    public ProvinceController(ProviceService proviceService) {
        this.proviceService = proviceService;
    }




}
