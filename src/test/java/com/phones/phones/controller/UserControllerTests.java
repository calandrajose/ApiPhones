/*
package com.phones.phones.controller;

import com.phones.phones.dto.UserDto;
import com.phones.phones.model.City;
import com.phones.phones.model.User;
import com.phones.phones.service.*;
import com.phones.phones.session.SessionManager;
import org.hibernate.usertype.UserType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserControllerTests {
    UserController userController;

    @Mock
    UserService userService;
    @Mock
    CallService callService;
    @Mock
    LineService lineService;
    @Mock
    CityService cityService;
    @Mock
    InvoiceService invoiceService;
    @Mock
    SessionManager sessionManager;

    @Before
    public void setUp() {
        initMocks(this);
        userController = new UserController(userService, callService, lineService, cityService, invoiceService, sessionManager);
    }


    @Test
    public void testLoginOk() throws UserNotExistsException, ValidationException {

        User loggedUser = new User(1, "carlos", "lolo", 38888765, null, "username", "password", "email", null, null, null);
        //cuando el mock llama a login con estos parametros devuelve loggedUser
        when(userService.login("user", "pwd")).thenReturn(loggedUser);
        User returnedUser = userController.login("user", "pwd");
        //verifica que los campos coincidan
        assertEquals(loggedUser.getId(), returnedUser.getId());
        assertEquals(loggedUser.getFirstname(), returnedUser.getFirstname());
        assertEquals(loggedUser.getSurname(), returnedUser.getSurname());
        assertEquals(loggedUser.getDni(), returnedUser.getDni());
        assertEquals(loggedUser.getBirthdate(), returnedUser.getBirthdate());
        assertEquals(loggedUser.getUsername(), returnedUser.getUsername());
        assertEquals(loggedUser.getPwd(), returnedUser.getPwd());
        assertEquals(loggedUser.getEmail(), returnedUser.getEmail());
        assertEquals(loggedUser.getUserType(), returnedUser.getUserType());
        assertEquals(loggedUser.getUserStatus(), returnedUser.getUserStatus());
        assertEquals(loggedUser.getCity(), returnedUser.getCity());
        //verifica que el metodo login ha sido llamado una vez
        verify(userService, times(1)).login("user", "pwd");

    }

    @Test(expected = UserNotExistsException.class)
    public void testLoginUserNotFound() throws UserNotExistsException, ValidationException {
        when(userService.login("user", "pwd")).thenThrow(new UserNotExistsException());
        userController.login("user", "pwd");
    }

    @Test(expected = ValidationException.class)
    public void testLoginIvalidadData() throws UserNotExistsException, ValidationException {
        userController.login(null, null);
    }

    @Test
    public void testAddOk() throws AlreadyExistsException {
        User loggedUser = new User(1, "carlos", "lolo", 38888765, null, "username", "password", "email", null, null, null);
        User user = new User(2, "ivan", "graciarena", 1, new Date(), "username", "pass", "email", UserType.EMPLOYEE, UserStatus.ACTIVE, new City());
        when(userService.add(user)).thenReturn(loggedUser);
        User returnedUser = userController.add(user);
        assertEquals(loggedUser.getId(), returnedUser.getId());
        assertEquals(loggedUser.getFirstname(), returnedUser.getFirstname());
        assertEquals(loggedUser.getSurname(), returnedUser.getSurname());
        assertEquals(loggedUser.getDni(), returnedUser.getDni());
        assertEquals(loggedUser.getBirthdate(), returnedUser.getBirthdate());
        assertEquals(loggedUser.getUsername(), returnedUser.getUsername());
        assertEquals(loggedUser.getPwd(), returnedUser.getPwd());
        assertEquals(loggedUser.getEmail(), returnedUser.getEmail());
        assertEquals(loggedUser.getUserType(), returnedUser.getUserType());
        assertEquals(loggedUser.getUserStatus(), returnedUser.getUserStatus());
        assertEquals(loggedUser.getCity(), returnedUser.getCity());
    }

    @Test(expected = AlreadyExistsException.class)
    public void testAddAlreadyExistsException() throws AlreadyExistsException {
        User user = new User(2, "ivan", "graciarena", 1, new Date(), "username", "pass", "email", UserType.EMPLOYEE, UserStatus.ACTIVE, new City());
        when(userService.add(user)).thenThrow(new AlreadyExistsException());
        userController.add(user);
    }

    @Test
    public void testRemoveUserOk() throws UserNotExistsException {
        doNothing().when(userService).removeUser(1);
        userController.removeUser(1);
        verify(userService, times(1)).removeUser(1);
    }

    @Test(expected = UserNotExistsException.class)
    public void testRemoveUserUserNotExistsException() throws UserNotExistsException {
        doThrow(new UserNotExistsException()).when(userService).removeUser(null);
        userController.removeUser(null);
    }

    @Test
    public void testUpdateUserOk() throws UserNotExistsException {
        User user = new User(2, "ivan", "graciarena", 1, new Date(), "username", "pass", "email", UserType.EMPLOYEE, UserStatus.ACTIVE, new City());
        doNothing().when(userService).updateUser(user);
        userController.updateUser(user);
        verify(userService, times(1)).updateUser(user);
    }

    @Test(expected = UserNotExistsException.class)
    public void testUpdateUserUserNotExistsException() throws UserNotExistsException {
        User user = new User(2, "ivan", "graciarena", 1, new Date(), "username", "pass", "email", UserType.EMPLOYEE, UserStatus.ACTIVE, new City());
        doThrow(new UserNotExistsException()).when(userService).updateUser(user);
        userController.updateUser(user);
    }

    @Test
    public void testGetByIdOk() throws UserNotExistsException {
        UserDto userDto = new UserDto("ivan", "graciarena", 333333, "ivanmdq22", "ivan@ivan.com", "mdp");
        when(userService.getById(1)).thenReturn(userDto);
        UserDto user = userController.getById(1);
        assertEquals(userDto.getCityName(), user.getCityName());
        assertEquals(userDto.getDni(), user.getDni());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getSurname(), userDto.getSurname());
        verify(userService, times(1)).getById(1);
    }

    @Test(expected = UserNotExistsException.class)
    public void testGetByIdUserNotExistsException() throws UserNotExistsException {
        when(userService.getById(1)).thenThrow(new UserNotExistsException());
        userController.getById(1);
    }

    @Test
    public void testGetMostCalledNumberOk() {
        UserMostCalledNumberDto userMostCalledNumberDto = new UserMostCalledNumberDto("123", "ivan", "graciarena");
        when(userService.getMostCalledNumber("111")).thenReturn(userMostCalledNumberDto);
        UserMostCalledNumberDto mostCalledNumberDto = userController.getMostCalledNumber("111");
        assertEquals(mostCalledNumberDto.getLineNumber(), userMostCalledNumberDto.getLineNumber());
        assertEquals(mostCalledNumberDto.getName(), userMostCalledNumberDto.getName());
        assertEquals(mostCalledNumberDto.getSurname(), userMostCalledNumberDto.getSurname());
        verify(userService, times(1)).getMostCalledNumber("111");
    }

    @Test
    public void testGetAllOk() {
        List<UserDto> list = new ArrayList<>();
        UserDto userDto = new UserDto("ivan", "graciarena", 333333, "ivanmdq22", "ivan@ivan.com", "mdp");
        list.add(userDto);
        when(userService.getAll()).thenReturn(list);
        List<UserDto> returnedList = userController.getAll();
        assertEquals(returnedList.size(), list.size());
        verify(userService, times(1)).getAll();
    }




    //no se como pasar token
    @Test(expected = UserNotExistException.class)
    public void testGetClientByIdNotFound() throws UserNotExistException, UserSessionNotExistException {
        when(userService.findById((long) 15)).thenThrow(new UserNotExistException());
        userController.findUserById("", (long) 15);
    }

    @Test
    public void testGetClientByOk() throws UserNotExistException, UserSessionNotExistException {
        User existingUser = User.builder().id((long) 6).name("").surname("")
                .city(new City()).userRoles(new ArrayList<>())
                .dni("").username("")
                .password("").isActive(true)
                .lines(new ArrayList<>()).build();

        when(userService.findById((long) 6)).thenReturn(existingUser);

        User clientTest = userController.findUserById("", (long) 6).getBody();


        Assert.assertEquals(existingUser.getUsername(), clientTest.getUsername());
        Assert.assertEquals(existingUser.getPassword(), clientTest.getPassword());
    }

}
*/
