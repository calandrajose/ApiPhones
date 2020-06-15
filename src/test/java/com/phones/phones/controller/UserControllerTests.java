package com.phones.phones.controller;

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
