package com.phones.phones.controller;

import com.phones.phones.dto.LineStatusDto;
import com.phones.phones.exception.line.LineNotExistException;
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
    public LineController(final LineService lineService) {
        this.lineService = lineService;
    }


    @PostMapping("/")
    public void createLine(@RequestBody @Valid final Line line) throws LineNumberAlreadyExistException {
        lineService.add(line);
    }

    //@GetMapping("/")
    public List<Line> getAllLines() {
        return lineService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Line> getLineById(@PathVariable final Long id) throws LineNotExistException {
        return lineService.getById(id);
    }

    @DeleteMapping("/{id}")
    public int deleteLineById(@PathVariable final Long id) throws LineNotExistException {
        return lineService.disableById(id);
    }

    @PutMapping("/{id}")
    public Optional<Line> updateLineStatusById(@RequestBody @Valid final LineStatusDto lineStatus,
                                               @PathVariable final Long id) throws LineNotExistException {
        return lineService.updateLineStatusByIdLine(lineStatus, id);
    }

    public List<Line> getLinesByUserId(final Long id) {
        return lineService.getByUserId(id);
    }

}
