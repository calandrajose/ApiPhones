package com.phones.phones.service;

import com.phones.phones.dto.UserDTO;
import com.phones.phones.exception.user.UserAlreadyExistException;
import com.phones.phones.exception.user.UserNotExistException;
import com.phones.phones.exception.user.UsernameAlreadyExistException;
import com.phones.phones.model.City;
import com.phones.phones.model.Province;
import com.phones.phones.model.User;
import com.phones.phones.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CityService cityService;
    private final ProvinceService provinceService;

    @Autowired
    public UserService(UserRepository userRepository, CityService cityService, ProvinceService provinceService) {
        this.userRepository = userRepository;
        this.cityService = cityService;
        this.provinceService = provinceService;
    }


    public void add(User newUser) throws UserAlreadyExistException, UsernameAlreadyExistException {
        Optional<User> user = userRepository.findByDni(newUser.getDni());
        if (user.isPresent()) {
            throw new UserAlreadyExistException("The dni already exists.");
        }
        if (userRepository.findByUsername(newUser.getUsername()) > 0) {
            throw new UsernameAlreadyExistException("The username already exists.");
        }
        userRepository.save(newUser);
    }

    /*
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();

        return users
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    */

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) throws UserNotExistException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotExistException("User does not exist.");
        }
        //return convertToDTO(user.get());
        return user.get();
    }

    public int disableById(Long id) throws UserNotExistException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotExistException("User does not exist.");
        }
        return userRepository.disableById(id);
    }

    public User updateById(Long id, User updatedUser) throws UserNotExistException {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotExistException("User does not exist.");
        }

        user.get().setName(updatedUser.getName());
        user.get().setSurname(updatedUser.getSurname());
        user.get().setDni(updatedUser.getDni());
        user.get().setUsername(updatedUser.getUsername());
        user.get().setPassword(updatedUser.getPassword());
        user.get().setCity(updatedUser.getCity());

        return userRepository.save(user.get());
    }

    // verify
    private UserDTO convertToDTO(User user) {
        Optional<City> city = cityService.getByUserId(user.getId());
        Optional<Province> province = provinceService.getByUserId(user.getId());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setDni(user.getDni());
        userDTO.setUsername(user.getUsername());
        userDTO.setIsActive(user.isActive());
        userDTO.setCity(city.isPresent() ? city.get().getName() : "");
        userDTO.setProvince(province.isPresent() ? province.get().getName() : "");

        return userDTO;
    }

}
