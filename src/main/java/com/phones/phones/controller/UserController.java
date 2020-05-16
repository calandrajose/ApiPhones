package com.phones.phones.controller;

import com.phones.phones.dto.UserDTO;
import com.phones.phones.exception.user.UserAlreadyExistException;
import com.phones.phones.exception.user.UserNotExistException;
import com.phones.phones.exception.user.UsernameAlreadyExistException;
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

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/")
    public void add(@RequestBody @Valid final User user) {
        try {
            userService.add(user);
            //return ResponseEntity.status(HttpStatus.CREATED).body();
        } catch (UserAlreadyExistException e) {
            e.printStackTrace();
        } catch (UsernameAlreadyExistException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> users = userService.getAll();
        return (users.size() > 0) ? ResponseEntity.ok(users) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable final Long id) throws UserNotExistException {
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public int disableUserById(@PathVariable final Long id) throws UserNotExistException {
        return userService.disableById(id);
    }

    /*
    @PutMapping("/{id}")
    public User updateUserById(@RequestBody User user, @PathVariable final Long id) {
        Optional<User> u = userService.getById(id);
        if(!u.isPresent()) {
            //return ;
        }
        return userService.updateById(id);
    }

    @GetMapping("/{id}/calls")
    public List<Call> getCallsByUserId(@PathVariable final Long id) {
        return null;
    }
    */

}
