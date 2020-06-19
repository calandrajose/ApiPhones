package com.phones.phones.controller;

import com.phones.phones.TestFixture;
import com.phones.phones.dto.UserDto;
import com.phones.phones.exception.call.CallDoesNotExistException;
import com.phones.phones.exception.user.UserAlreadyDisableException;
import com.phones.phones.exception.user.UserDoesNotExistException;
import com.phones.phones.exception.user.UserSessionDoesNotExistException;
import com.phones.phones.exception.user.UsernameAlreadyExistException;
import com.phones.phones.model.Call;
import com.phones.phones.model.User;
import com.phones.phones.service.*;
import com.phones.phones.session.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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


/*    @Test
    public void createUserOk() throws UserSessionDoesNotExistException, UserAlreadyExistException, UsernameAlreadyExistException {
        User loggedUser = TestFixture.testUser();
        User newUser = TestFixture.testClientUser();
        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(userService.create(newUser)).thenReturn(newUser);

        ResponseEntity <User> createdUser = userController.createUser("123", newUser);

        assertEquals(loggedUser.getId(), createdUser.getBody().getId());
        assertEquals(loggedUser.getDni(), createdUser.getBody().getDni());
       // assertEquals(loggedUser.getLines().size(), createdUser.getBody().getLines().size());
    }*/


    @Test
    public void findAllUsersOk() throws UserSessionDoesNotExistException {
        User loggedUser = TestFixture.testUser();
        List<User> users = TestFixture.testListOfUsers();
        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(userService.findAll()).thenReturn(users);

        ResponseEntity<List<User>> dbUsers = userController.findAllUsers("123");

        assertEquals(users.size(), dbUsers.getBody().size());
        assertEquals(users.get(0).getDni(), dbUsers.getBody().get(0).getDni());
    }


    @Test
    public void findAllCallsUserIsNotEmployee() throws UserSessionDoesNotExistException {
        User loggedUser = TestFixture.testClientUser();
        ResponseEntity response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);

        ResponseEntity<List<User>> returnedUsers = userController.findAllUsers("123");
        assertEquals(response.getStatusCode(), returnedUsers.getStatusCode());
    }


    @Test
    public void findAllUsersNoUsersFound() throws UserSessionDoesNotExistException {
        User loggedUser = TestFixture.testUser();
        List<User> noUsers = new ArrayList<>();
        ResponseEntity response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(userService.findAll()).thenReturn(noUsers);

        ResponseEntity<List<User>> returnedUsers = userController.findAllUsers("123");

        assertEquals(response.getStatusCode(), returnedUsers.getStatusCode());
    }

    @Test
    public void findUserByIdOk() throws UserSessionDoesNotExistException, CallDoesNotExistException, UserDoesNotExistException {
        User loggedUser = TestFixture.testUser();
        User user = TestFixture.testClientUser();

        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(userService.findById(1L)).thenReturn(user);

        ResponseEntity<User> returnedUser = userController.findUserById("123", 1L);

        assertEquals(user.getId(), returnedUser.getBody().getId());
        assertEquals(user.getDni(), returnedUser.getBody().getDni());
        assertEquals(user.getSurname(), returnedUser.getBody().getSurname());
        assertEquals(1L, returnedUser.getBody().getId());
    }

    @Test
    public void findAllCallByIdUserIsNotEmployee() throws UserSessionDoesNotExistException, CallDoesNotExistException, UserDoesNotExistException {
        User loggedUser = TestFixture.testClientUser();
        ResponseEntity response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);

        ResponseEntity<User> returnedUser = userController.findUserById("123", 1L);
        assertEquals(response.getStatusCode(), returnedUser.getStatusCode());
    }

    @Test
    public void deleteUserByIdOk() throws UserSessionDoesNotExistException, UserDoesNotExistException, UserAlreadyDisableException {
        User loggedUser = TestFixture.testUser();

        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(userService.disableById(1L)).thenReturn(true);

        ResponseEntity deleted = userController.deleteUserById("123", 1L);

        assertEquals(ResponseEntity.ok().build(), deleted);
    }

    @Test
    public void deleteUserByIdUserIsNotEmployee() throws UserSessionDoesNotExistException, CallDoesNotExistException, UserDoesNotExistException, UserAlreadyDisableException {
        User loggedUser = TestFixture.testClientUser();
        ResponseEntity response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);

        ResponseEntity deleted = userController.deleteUserById("123", 1L);
        assertEquals(response.getStatusCode(), deleted.getStatusCode());
    }


    @Test
    public void updateUserByIdOk() throws UserSessionDoesNotExistException, UserDoesNotExistException, UserAlreadyDisableException, UsernameAlreadyExistException {
        User loggedUser = TestFixture.testUser();
        User updatedUser = TestFixture.testClientUser();
        UserDto userUpdate = TestFixture.testUserDto();

        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(userService.updateById(1L, userUpdate)).thenReturn(updatedUser);

        ResponseEntity updated = userController.updateUserById("123", userUpdate, 1L);

        assertEquals(ResponseEntity.ok().build(), updated);
    }

    @Test
    public void updateUserByIdUserIsNotEmployee() throws UserSessionDoesNotExistException, UserDoesNotExistException, UserAlreadyDisableException, UsernameAlreadyExistException {
        User loggedUser = TestFixture.testClientUser();
        UserDto userUpdate = TestFixture.testUserDto();
        ResponseEntity response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);

        ResponseEntity updated = userController.updateUserById("123", userUpdate,1L);
        assertEquals(response.getStatusCode(), updated.getStatusCode());
    }

    @Test
    public void findCallsByUserIdOk() throws UserSessionDoesNotExistException, CallDoesNotExistException, UserDoesNotExistException {
        User loggedUser = TestFixture.testUser();
        List<Call> testCalls = TestFixture.testListOfCalls();

        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(callService.findByUserId(1L)).thenReturn(testCalls);

        ResponseEntity<List<Call>> returnedCalls = userController.findCallsByUserId("123", 1L);

        assertEquals(testCalls.size(), returnedCalls.getBody().size());
        assertEquals(testCalls.get(0).getOriginNumber(), returnedCalls.getBody().get(0).getOriginNumber());
    }

    @Test
    public void findCallsByUserIdUserIsNotEmployee() throws UserSessionDoesNotExistException, UserDoesNotExistException, UserAlreadyDisableException, UsernameAlreadyExistException {
        User loggedUser = TestFixture.testClientUser();

        ResponseEntity response = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);

        ResponseEntity<List<Call>> returnedCalls = userController.findCallsByUserId("123", 1L);
        assertEquals(response.getStatusCode(), returnedCalls.getStatusCode());
    }


    @Test
    public void findAllCallsByUserIdNoCallsDone() throws UserSessionDoesNotExistException, UserDoesNotExistException {
        User loggedUser = TestFixture.testUser();
        List<Call> emptyCalls = new ArrayList<>();

        ResponseEntity response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(callService.findByUserId(1L)).thenReturn(emptyCalls);

        ResponseEntity<List<Call>> returnedCalls = userController.findCallsByUserId("123", 1L);

        assertEquals(response.getStatusCode(), returnedCalls.getStatusCode());
    }
/*


*/
}
