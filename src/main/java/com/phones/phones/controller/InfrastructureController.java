package com.phones.phones.controller;

import com.phones.phones.dto.InfrastructureCallDto;
import com.phones.phones.exception.user.UserInvalidLoginException;
import com.phones.phones.service.CallService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    public ResponseEntity createCall(@RequestBody @Valid final InfrastructureCallDto infrastructureCallDto) throws UserInvalidLoginException {
        if (!login(infrastructureCallDto.getUser(), infrastructureCallDto.getPassword())) {
            throw new UserInvalidLoginException();
        }

        // llamar callService y añadir la llamada
        System.out.println(infrastructureCallDto);

        return ResponseEntity.ok().build();
    }

    private boolean login(String username, String password) {
        return (this.username.equals(username) && this.password.equals(password));
    }

}
