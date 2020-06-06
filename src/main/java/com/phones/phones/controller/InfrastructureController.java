package com.phones.phones.controller;

import com.phones.phones.dto.InfrastructureCallDto;
import com.phones.phones.exception.line.LineCannotMakeCallsException;
import com.phones.phones.exception.line.LineNumberNotExistException;
import com.phones.phones.exception.user.UserInvalidLoginException;
import com.phones.phones.model.Call;
import com.phones.phones.service.CallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/infrastructure")
public class InfrastructureController {

    private final CallService callService;
    private final String username = "test";
    private final String password = "1234";

    public InfrastructureController(final CallService callService) {
        this.callService = callService;
    }

    /*
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid final UserLoginDto userLoginDto) {
        return null;
    }
     */

    @PostMapping("/call")
    public ResponseEntity createCall(@RequestBody @Valid final InfrastructureCallDto infrastructureCallDto) throws LineNumberNotExistException, LineCannotMakeCallsException, UserInvalidLoginException {
        if (!login(infrastructureCallDto.getUser(), infrastructureCallDto.getPassword())) {
            throw new UserInvalidLoginException();
        }
        Call newCall = callService.create(infrastructureCallDto);
        return ResponseEntity.created(getLocation(newCall)).build();
    }

    private boolean login(String username, String password) {
        return (this.username.equals(username) && this.password.equals(password));
    }

    // arreglar esto, uri incorrecta
    private URI getLocation(Call call) {
        String template = "/api/calls";
        return ServletUriComponentsBuilder
                .fromUriString(template)
                .path("/{id}")
                .buildAndExpand(call.getId())
                .toUri();
    }

}
