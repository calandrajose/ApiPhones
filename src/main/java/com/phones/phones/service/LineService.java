package com.phones.phones.service;

import com.phones.phones.model.Line;
import com.phones.phones.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineService {

    private final LineRepository lineRepository;

    @Autowired
    public LineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }


    public List<Line> getAll() {
        return lineRepository.findAll();
    }

}
