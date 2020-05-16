package com.phones.phones.controller;

import com.phones.phones.model.Call;
import com.phones.phones.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/calls")
public class CallController {

    private final CallService callService;

    @Autowired
    public CallController(CallService callService) {
        this.callService = callService;
    }


    @PostMapping("/")
    public void add(@RequestBody @Valid final Call call) {
        callService.add(call);
    }

    @GetMapping("/")
    public List<Call> getAll() {
        return callService.getAll();
    }


}
