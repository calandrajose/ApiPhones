package com.phones.phones.controller;

import com.phones.phones.exception.line.LineNumberAlreadyExistException;
import com.phones.phones.model.Line;
import com.phones.phones.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lines")
public class LineController {

    private final LineService lineService;

    @Autowired
    public LineController(LineService lineService) {
        this.lineService = lineService;
    }


    @PostMapping("/")
    public void add(@RequestBody @Valid final Line line) throws LineNumberAlreadyExistException {
        lineService.add(line);
    }

    @GetMapping("/")
    public List<Line> getAll() {
        return lineService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Line> getById(Long id) {
        return lineService.getById(id);
    }

}
