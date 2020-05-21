package com.phones.phones.controller;

import com.phones.phones.dto.UserLoginDto;
import com.phones.phones.exception.user.UserInvalidLoginException;
import com.phones.phones.exception.user.UserNotExistException;
import com.phones.phones.model.User;
import com.phones.phones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class LoginController {

    UserController userController;
    SessionManager sessionManager;

    @Autowired
    public LoginController(UserController userController, SessionManager sessionManager) {
        this.userController = userController;
        this.sessionManager = sessionManager;
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginDto loginRequestDto) throws UserInvalidLoginException, ValidationException {
        ResponseEntity response;
        try {
            Optional<User> u = userController.login(loginRequestDto.getUsername(), loginRequestDto.getPassword());

            // fix
            if (u.isEmpty()) {
                return null;
            }

            String token = sessionManager.createSession(u.get());
            response = ResponseEntity.ok().headers(createHeaders(token)).build();
        } catch (UserNotExistException e) {
            throw new UserInvalidLoginException();
        }
        return response;
    }


    @PostMapping("/logout")
    public ResponseEntity logout(@RequestHeader("Authorization") String token) {
        sessionManager.removeSession(token);
        return ResponseEntity.ok().build();
    }

    private HttpHeaders createHeaders(String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", token);
        return responseHeaders;
    }

}
