package com.phones.phones.controller;

import com.phones.phones.exception.user.UserNotExistException;
import com.phones.phones.exception.user.UserSessionNotExistException;
import com.phones.phones.model.City;
import com.phones.phones.model.User;
import com.phones.phones.service.UserService;
import org.hibernate.usertype.UserType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserControllerTests {
/*
    UserController userController;

    @Mock
    UserService userService;

    @Before
    public void setUp() {
        initMocks(this);
        userController = new UserController(userService);
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
 */

}
