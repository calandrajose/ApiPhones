package com.phones.phones.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.phones.phones.model.User;
import com.phones.phones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public void add(@RequestBody @Valid final User user) {
        userService.add(user);
        //System.out.println(user);
    }

    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAll();
    }

}
