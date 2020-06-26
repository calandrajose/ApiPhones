package com.phones.phones.controller.web;

import com.phones.phones.controller.UserController;
import com.phones.phones.session.SessionManager;
import org.junit.Before;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.initMocks;

public class LoginControllerTest {

    LoginController loginController;

    @Mock
    UserController userController;
    @Mock
    SessionManager sessionManager;

    @Before
    public void setUp() {
        initMocks(this);
        loginController = new LoginController(userController, sessionManager);
    }


/*    @Test
    public void loginOk() throws UserInvalidLoginException, UserDoesNotExistException {
        UserLoginDto userLoginDto = TestFixture.testUserLoginDto();
        User user = TestFixture.testUser();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token");
        ResponseEntity response = ResponseEntity.ok().headers(headers).build();

        when(userController.login(userLoginDto.getUsername(), userLoginDto.getPassword())).thenReturn(Optional.ofNullable(user));
        when(sessionManager.createSession(user)).thenReturn("token");
        ResponseEntity returnedResponse = loginController.login(TestFixture.testUserLoginDto());
        assertEquals(response.getHeaders().toString(), response.getHeaders().toString());
    }

    @Test(expected = UserInvalidLoginException.class)
    public void loginUserInvalidLoginException() throws UserInvalidLoginException, UserDoesNotExistException {
        UserLoginDto userLoginDto = TestFixture.testUserLoginDto();

        when(userController.login(userLoginDto.getUsername(), userLoginDto.getPassword())).thenReturn(Optional.empty());
        loginController.login(TestFixture.testUserLoginDto());

    }*/
}
