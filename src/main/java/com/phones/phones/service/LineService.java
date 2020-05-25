package com.phones.phones.service;

import com.phones.phones.dto.LineStatusDto;
import com.phones.phones.exception.line.LineNotExistException;
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
    public LineService(final LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }


    public void create(Line newLine) throws LineNumberAlreadyExistException {
        Optional<Line> line = lineRepository.findByNumber(newLine.getNumber());
        if (line.isPresent()) {
            throw new LineNumberAlreadyExistException();
        }
        lineRepository.save(newLine);
    }

    public List<Line> findAll() {
        return lineRepository.findAll();
    }

    public Optional<Line> findById(Long id) throws LineNotExistException {
        Optional<Line> line = lineRepository.findById(id);
        if (line.isEmpty()) {
            throw new LineNotExistException();
        }
        return lineRepository.findById(id);
    }

    public List<Line> findByUserId(Long id) {
        return lineRepository.findAllByUserId(id);
    }

    public int disableById(Long id) throws LineNotExistException {
        Optional<Line> line = lineRepository.findById(id);
        if (line.isEmpty()) {
            throw new LineNotExistException();
        }
        return lineRepository.disableById(id);
    }

    public Optional<Line> updateLineStatusByIdLine(LineStatusDto lineStatus,
                                                   Long id) throws LineNotExistException {
        Optional<Line> line = lineRepository.findById(id);
        if (line.isEmpty()) {
            throw new LineNotExistException();
        }
        line.get().setStatus(lineStatus.getStatus());
        return line;
    }

}
