package com.phones.phones.service;

import com.phones.phones.exception.line.LineNumberAlreadyExistException;
import com.phones.phones.model.Line;
import com.phones.phones.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineService {

    private final LineRepository lineRepository;

    @Autowired
    public LineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }


    public void add(Line newLine) throws LineNumberAlreadyExistException {
        Optional<Line> line = lineRepository.findByNumber(newLine.getNumber());

        if (line.isPresent()) {
            throw new LineNumberAlreadyExistException("Line number already exist.");
        }

        lineRepository.save(newLine);
    }

    public List<Line> getAll() {
        return lineRepository.findAll();
    }

    public Optional<Line> getById(Long id) {
        return lineRepository.findById(id);
    }
}
