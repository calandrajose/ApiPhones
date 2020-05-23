package com.phones.phones.service;

import com.phones.phones.dto.RateDto;
import com.phones.phones.repository.dto.RateDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService {

    private final RateDtoRepository rateDtoRepository;

    @Autowired
    public RateService(RateDtoRepository rateDtoRepository) {
        this.rateDtoRepository = rateDtoRepository;
    }


    public List<RateDto> getAll() {
        return rateDtoRepository.findAll();
    }

}
