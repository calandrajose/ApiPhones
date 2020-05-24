package com.phones.phones.service;

import com.phones.phones.dto.UserDto;
import com.phones.phones.exception.user.UserAlreadyExistException;
import com.phones.phones.exception.user.UserNotExistException;
import com.phones.phones.exception.user.UsernameAlreadyExistException;
import com.phones.phones.model.User;
import com.phones.phones.repository.dto.UserDtoRepository;
import com.phones.phones.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoRepository userDtoRepository;

    @Autowired
    public UserService(final UserRepository userRepository, final UserDtoRepository userDtoRepository) {
        this.userRepository = userRepository;
        this.userDtoRepository = userDtoRepository;
    }


    public User add(User newUser) throws UserAlreadyExistException, UsernameAlreadyExistException {
        Optional<User> user = userRepository.findByDni(newUser.getDni());
        if (user.isPresent()) {
            throw new UserAlreadyExistException("The dni already exists.");
        }
        if (existsUsername(newUser.getUsername())) {
            throw new UsernameAlreadyExistException("The username already exists.");
        }
        return userRepository.save(newUser);
    }

    public List<UserDto> getAll() {
        return userDtoRepository.findAll();
    }

    public Optional<UserDto> getById(Long id) throws UserNotExistException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotExistException("User does not exist.");
        }
        return userDtoRepository.findById(id);
    }

    public int disableById(Long id) throws UserNotExistException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotExistException("User does not exist.");
        }
        return userRepository.disableById(id);
    }

    public User updateById(Long id, User updatedUser) throws UserNotExistException, UsernameAlreadyExistException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotExistException("User does not exist.");
        }
        if (existsUsername(updatedUser.getUsername())) {
            throw new UsernameAlreadyExistException("The username already exists.");
        }
        user.get().setName(updatedUser.getName());
        user.get().setSurname(updatedUser.getSurname());
        user.get().setDni(updatedUser.getDni());
        user.get().setUsername(updatedUser.getUsername());
        user.get().setPassword(updatedUser.getPassword());
        user.get().setCity(updatedUser.getCity());

        return userRepository.save(user.get());
    }

    private boolean existsUsername(String username) {
        return userRepository.findByUsername(username) > 0;
    }

    public Optional<User> login(String username, String password) throws UserNotExistException {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isEmpty()) {
            throw new UserNotExistException("");
        }
        return user;
    }

}
