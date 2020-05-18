package com.phones.phones.controller;

import com.phones.phones.exception.user.UserAlreadyExistException;
import com.phones.phones.exception.user.UserNotExistException;
import com.phones.phones.exception.user.UsernameAlreadyExistException;
import com.phones.phones.model.Call;
import com.phones.phones.model.Line;
import com.phones.phones.model.User;
import com.phones.phones.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final CallController callController;
    private final LineController lineController;

    @Autowired
    public UserController(UserService userService, CallController callController, LineController lineController) {
        this.userService = userService;
        this.callController = callController;
        this.lineController = lineController;
    }


    @PostMapping("/")
    public void addUser(@RequestBody @Valid final User user) throws UsernameAlreadyExistException, UserAlreadyExistException {
        userService.add(user);
        //return ResponseEntity.status(HttpStatus.CREATED).body();
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        //List<UserDTO> users = userService.getAll();
        List<User> users = userService.getAll();
        return (users.size() > 0) ? ResponseEntity.ok(users) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable final Long id) throws UserNotExistException {
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public int deleteUserById(@PathVariable final Long id) throws UserNotExistException {
        return userService.disableById(id);
    }

    @PutMapping("/{id}")
    public User updateUserById(@RequestBody User user, @PathVariable final Long id) throws UserNotExistException {
        return userService.updateById(id, user);
    }

    @GetMapping("/{id}/calls")
    public List<Call> getCallsByUserId(@PathVariable final Long id) throws UserNotExistException {
        return callController.getCallsByUserId(id);
    }

    @GetMapping("/{id}/lines")
    public List<Line> getLinesByUserId(@PathVariable final Long id) {
        return lineController.getLinesByUserId(id);
    }

}
