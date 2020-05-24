package com.phones.phones.controller;

import com.phones.phones.dto.RateDto;
import com.phones.phones.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<RateDto>> getAllRates() {
        List<RateDto> rates = rateService.getAll();
        return (rates.size() > 0) ? ResponseEntity.ok(rates) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
