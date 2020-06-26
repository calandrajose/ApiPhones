package com.phones.phones.controller;

import com.phones.phones.RestUtils;
import com.phones.phones.TestFixture;
import com.phones.phones.exception.province.ProviceAlreadyExistException;
import com.phones.phones.exception.province.ProvinceDoesNotExistException;
import com.phones.phones.exception.user.UserSessionDoesNotExistException;
import com.phones.phones.model.Province;
import com.phones.phones.model.User;
import com.phones.phones.service.ProvinceService;
import com.phones.phones.session.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@PrepareForTest(RestUtils.class)
@RunWith(PowerMockRunner.class)
public class ProvinceControllerTest {
    ProvinceController provinceController;

    @Mock
    ProvinceService provinceService;
    @Mock
    SessionManager sessionManager;


    @Before
    public void setUp() {
        initMocks(this);
        PowerMockito.mockStatic(RestUtils.class);
        provinceController = new ProvinceController(provinceService, sessionManager);
    }



    @Test
    public void createProvinceOk() throws UserSessionDoesNotExistException, ProviceAlreadyExistException {
        Province newProvince = TestFixture.testProvince();
        when(sessionManager.getCurrentUser("token")).thenReturn(TestFixture.testUser());
        when(provinceService.create(newProvince)).thenReturn(newProvince);
        when(RestUtils.getLocation(newProvince.getId())).thenReturn(URI.create("miUri.com"));
        ResponseEntity response = provinceController.createProvince("token" , newProvince);
        assertEquals(URI.create("miUri.com"), response.getHeaders().getLocation());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void findAllProvincesOk() throws UserSessionDoesNotExistException {
        User loggedUser = TestFixture.testUser();
        List<Province> listOfProvinces = TestFixture.testListOfProvinces();

        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(provinceService.findAll()).thenReturn(listOfProvinces);

        ResponseEntity<List<Province>> returnedProvinces = provinceController.findAllProvinces("123");

        assertEquals(listOfProvinces.size(), returnedProvinces.getBody().size());
        assertEquals(listOfProvinces.get(0).getName(), returnedProvinces.getBody().get(0).getName());
        assertEquals(listOfProvinces.get(0).getId(), returnedProvinces.getBody().get(0).getId());
        assertEquals(listOfProvinces.get(0).getCities().size(), returnedProvinces.getBody().get(0).getCities().size());

    }


    @Test
    public void findAllProvincesNoProvincesFound() throws UserSessionDoesNotExistException {
        User loggedUser = TestFixture.testUser();
        List<Province> emptyList = new ArrayList<>();
        ResponseEntity response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(provinceService.findAll()).thenReturn(emptyList);

        ResponseEntity<List<Province>> returnedProvinces = provinceController.findAllProvinces("123");

        assertEquals(response.getStatusCode(), returnedProvinces.getStatusCode());
    }

    @Test
    public void findProvinceByIdOk() throws UserSessionDoesNotExistException, ProvinceDoesNotExistException {
        User loggedUser = TestFixture.testUser();
        Province province = TestFixture.testProvince();

        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(provinceService.findById(1L)).thenReturn(province);

        ResponseEntity<Province> returnedProvinces = provinceController.findProvinceById("123", 1L);

        assertEquals(province.getId(), returnedProvinces.getBody().getId());
        assertEquals(province.getName(), returnedProvinces.getBody().getName());
        assertEquals(province.getCities().size(), returnedProvinces.getBody().getCities().size());
        assertEquals(1L, returnedProvinces.getBody().getId());
    }


    /**todo getLocation
            create
     **/

}
