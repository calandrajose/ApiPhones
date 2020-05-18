package com.phones.phones.controller;

import com.phones.phones.model.Rate;
import com.phones.phones.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rates")
public class RateController {

    private final RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }


    @GetMapping("/")
    public List<Rate> getAllRates() {
        return rateService.getAll();
    }

}
