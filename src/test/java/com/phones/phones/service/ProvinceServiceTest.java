package com.phones.phones.service;

import com.phones.phones.TestFixture;
import com.phones.phones.exception.province.ProviceAlreadyExistException;
import com.phones.phones.exception.user.UserAlreadyDisableException;
import com.phones.phones.exception.user.UserNotExistException;
import com.phones.phones.model.Province;
import com.phones.phones.repository.ProvinceRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProvinceServiceTest {

    ProvinceService provinceService;

    @Mock
    ProvinceRepository provinceRepository;

    @Before
    public void setUp() {
        initMocks(this);
        this.provinceService = new ProvinceService(provinceRepository);
    }

    @Test
    public void testCreateOk() throws ProviceAlreadyExistException {
        Province newProvince = TestFixture.testProvince();

        when(provinceRepository.findByName(newProvince.getName())).thenReturn(null);

        Province returnedProvince = this.provinceService.create(newProvince);
        System.out.println(returnedProvince);

        assertEquals(newProvince.getId(), returnedProvince.getId());
        assertEquals(newProvince.getName(), returnedProvince.getName());
    }
/*
    @Test(expected = UserAlreadyExistException.class)
    public void testAddExistingUser() throws UsernameAlreadyExistException, UserAlreadyExistException {

        User newUser = TestFixture.testUser();
        when(provinceRepository.findByName(newProvince.getName())).thenReturn(Optional.ofNullable(newProvince)
        userService.create(newUser);
    }

    @Test
    public void testFindAllOk() {
        List<User> allUsers = TestFixture.testListOfUsers();
        when(userRepository.findAll()).thenReturn(allUsers);

        List<User> returnedUsers = userService.findAll();

        assertEquals(returnedUsers.size(), allUsers.size());
        assertEquals(returnedUsers.get(0).getId(), allUsers.get(0).getId());
    }

    @Test
    public void testFindAllEmpty() {
        List<User> emptyList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(emptyList);

        List<User> returnedUsers = userService.findAll();
        assertEquals(returnedUsers.size(), 0);
    }

    @Test
    public void testFindByIdOk() throws UserNotExistException {
        User userGetById = User
                .builder()
                .id(1L)
                .name("Rodrigo")
                .surname("Leon")
                .dni("404040")
                .username("rl")
                .password("123")
                .isActive(true)
                .city(new City())
                .build();

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(userGetById));

        User returnedUser = this.userService.findById(1L);

        assertEquals(userGetById.getId(), returnedUser.getId());
    }


    @Test(expected = UserNotExistException.class)
    public void testFindByIdUserNotExist() throws UserNotExistException {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        this.userService.findById(2L);
    }

*/
    /***
     * Returns True if disabled
     * @throws UserNotExistException
     * @throws UserAlreadyDisableException
     *//*
    @Test
    public void testDisableByIdOk() throws UserNotExistException, UserAlreadyDisableException {
        User disabledUser = TestFixture.testUser();
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(disabledUser));
        when(userRepository.disableById(1L)).thenReturn(1);
        boolean desableDTrue = userService.disableById(disabledUser.getId());
        assertEquals(true, desableDTrue);
    }


    @Test(expected = UserNotExistException.class)
    public void testDisableByIdUserNotExist() throws UserNotExistException, UserAlreadyDisableException {

        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        this.userService.disableById(2L);
    }

    @Test(expected = UserAlreadyDisableException.class)
    public void testDisableByIdUserIsNotActive() throws UserNotExistException, UserAlreadyDisableException {
        User disabledUser = TestFixture.testDisabledUser();

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(disabledUser));
        this.userService.disableById(1L);
    }

    @Test
    public void testLoginOk() throws UserInvalidLoginException {
        User loggedUser = TestFixture.testUser();
        when(userRepository.findByUsername("rl")).thenReturn(Optional.ofNullable(loggedUser));
        when(passwordEncoder.matches("123", loggedUser.getPassword())).thenReturn(true);
        Optional<User> returnedUser = userService.login("rl","123");

        assertEquals(loggedUser.getId(), returnedUser.get().getId());
        assertEquals(loggedUser.getUsername(), returnedUser.get().getUsername());
        verify(userRepository, times(1)).findByUsername("rl");
    }
    */
}
