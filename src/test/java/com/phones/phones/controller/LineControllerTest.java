package com.phones.phones.controller;

import com.phones.phones.utils.RestUtils;
import com.phones.phones.TestFixture;
import com.phones.phones.dto.LineDto;
import com.phones.phones.exception.line.LineAlreadyDisabledException;
import com.phones.phones.exception.line.LineDoesNotExistException;
import com.phones.phones.exception.line.LineNumberAlreadyExistException;
import com.phones.phones.exception.user.UserSessionDoesNotExistException;
import com.phones.phones.model.Call;
import com.phones.phones.model.Line;
import com.phones.phones.model.User;
import com.phones.phones.service.CallService;
import com.phones.phones.service.LineService;
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
public class LineControllerTest {
    LineController lineController;

    @Mock
    LineService lineService;
    @Mock
    CallService callService;
    @Mock
    SessionManager sessionManager;


    @Before
    public void setUp() {
        initMocks(this);
        PowerMockito.mockStatic(RestUtils.class);
        lineController = new LineController(lineService, callService, sessionManager);
    }



    @Test
    public void createLineOk() throws UserSessionDoesNotExistException, LineNumberAlreadyExistException {
        User loggedUser = TestFixture.testUser();
        Line newLine = TestFixture.testLine("2235472861");

        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(lineService.create(newLine)).thenReturn(newLine);
        when(RestUtils.getLocation(newLine.getId())).thenReturn(URI.create("miUri.com"));

        ResponseEntity response = lineController.createLine("123", newLine);

        assertEquals(URI.create("miUri.com"), response.getHeaders().getLocation());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    /**
     *
     * findAllLines
     *
     * */


    @Test
    public void findAllLinesOk() throws UserSessionDoesNotExistException {
        User loggedUser = TestFixture.testUser();
        List<Line> listOfLines = TestFixture.testListOfLines();

        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(lineService.findAll()).thenReturn(listOfLines);

        ResponseEntity<List<Line>> returnedLines = lineController.findAllLines("123");

        assertEquals(listOfLines.size(), returnedLines.getBody().size());
        assertEquals(listOfLines.get(0).getStatus(), returnedLines.getBody().get(0).getStatus());
        assertEquals(listOfLines.get(0).getId(), returnedLines.getBody().get(0).getId());
    }


    @Test
    public void findAllLinesNoLinesFound() throws UserSessionDoesNotExistException {
        User loggedUser = TestFixture.testUser();
        List<Line> emptyList = new ArrayList<>();
        ResponseEntity response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(lineService.findAll()).thenReturn(emptyList);

        ResponseEntity<List<Line>> returnedLines = lineController.findAllLines("123");

        assertEquals(response.getStatusCode(), returnedLines.getStatusCode());
    }


    /**
     *
     * findLineById
     *
     * */


    @Test
    public void findLineByIdOk() throws UserSessionDoesNotExistException, LineDoesNotExistException {
        User loggedUser = TestFixture.testUser();
        Line line = TestFixture.testLine("2235472861");

        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(lineService.findById(1L)).thenReturn(line);

        ResponseEntity<Line> returnedLine = lineController.findLineById("123", 1L);

        assertEquals(line.getId(), returnedLine.getBody().getId());
        assertEquals(line.getStatus(), returnedLine.getBody().getStatus());
        assertEquals(line.getId(), returnedLine.getBody().getId());
        assertEquals(1L, returnedLine.getBody().getId());
    }


    /**
     *
     * findCallsByLineId
     *
     * */

    @Test
    public void findCallsByLineIdOk() throws UserSessionDoesNotExistException, LineDoesNotExistException {
        User loggedUser = TestFixture.testUser();
        List<Call> listOfCalls = TestFixture.testListOfCalls();

        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(callService.findByLineId(1L)).thenReturn(listOfCalls);

        ResponseEntity<List<Call>> returnedCalls = lineController.findCallsByLineId("123", 1L);

        assertEquals(listOfCalls.size(), returnedCalls.getBody().size());
        assertEquals(listOfCalls.get(0).getDuration(), returnedCalls.getBody().get(0).getDuration());
        assertEquals(listOfCalls.get(1).getId(), returnedCalls.getBody().get(1).getId());
    }

    @Test
    public void findCallsByLineIdNoCallsFound() throws UserSessionDoesNotExistException, LineDoesNotExistException {
        User loggedUser = TestFixture.testUser();
        List<Call> emptyList = new ArrayList<>();
        ResponseEntity response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(callService.findAll()).thenReturn(emptyList);

        ResponseEntity<List<Call>> returnedCalls = lineController.findCallsByLineId("123", 1L);

        assertEquals(response.getStatusCode(), returnedCalls.getStatusCode());
    }


    /**
     *
     * deleteLineByIdOk
     *
     * */

    @Test
    public void deleteLineByIdOk() throws UserSessionDoesNotExistException, LineAlreadyDisabledException, LineDoesNotExistException {
        User loggedUser = TestFixture.testUser();

        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(lineService.disableById(1L)).thenReturn(1);

        ResponseEntity deleted = lineController.deleteLineById("123", 1L);

        assertEquals(ResponseEntity.ok().build(), deleted);
    }


    /**
     *
     * updateLineByIdLine
     *
     * */


    @Test
    public void updateLineByIdLineOk() throws UserSessionDoesNotExistException, LineDoesNotExistException {
        User loggedUser = TestFixture.testUser();
        LineDto lineUpdate = TestFixture.testLineDto();

        when(sessionManager.getCurrentUser("123")).thenReturn(loggedUser);
        when(lineService.updateLineByIdLine(1L, lineUpdate)).thenReturn(true);

        ResponseEntity updated = lineController.updateLineByIdLine("123", lineUpdate, 1L);

        assertEquals(ResponseEntity.ok().build(), updated);
    }



    /**todo getLocation*/
}
