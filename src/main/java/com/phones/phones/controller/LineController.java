package com.phones.phones.controller;

import com.phones.phones.model.Line;
import com.phones.phones.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/lines")
public class LineController {

    private final LineService lineService;

    @Autowired
    public LineController(LineService lineService) {
        this.lineService = lineService;
    }


    @PostMapping("/")
    public void add(@RequestBody @Valid final Line line) {
        lineService.add(line);
    }

    @GetMapping("/")
    public List<Line> getAll() {
        return lineService.getAll();
    }

}
