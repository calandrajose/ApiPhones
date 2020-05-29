package com.phones.phones.controller;

import com.phones.phones.exception.user.UserNotExistException;
import com.phones.phones.model.Call;
import com.phones.phones.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/calls")
public class CallController {

    private final CallService callService;

    @Autowired
    public CallController(final CallService callService) {
        this.callService = callService;
    }


    //@PostMapping("/")
    public void createCall(@RequestBody @Valid final Call call) {
        callService.create(call);
    }

    //@GetMapping("/")
    public List<Call> findAllCalls() {
        return callService.findAll();
    }

    // verificar - el empleado puede ver las llamadas de todos y los clientes solo sus llamdas (por id)
    public List<Call> findCallsByUserId(final Long id) throws UserNotExistException {
        return callService.findByUserId(id);
    }

    public List<Call> findCallsByUserIdBetweenDates(final Long id,
                                                    final Date from,
                                                    final Date to) throws UserNotExistException {
        return callService.findByUserIdBetweenDates(id, from, to);
    }

}
