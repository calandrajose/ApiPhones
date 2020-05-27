package com.phones.phones.controller;

import com.phones.phones.dto.LineStatusDto;
import com.phones.phones.exception.line.LineNotExistException;
import com.phones.phones.exception.line.LineNumberAlreadyExistException;
import com.phones.phones.exception.user.UserNotExistException;
import com.phones.phones.exception.user.UserSessionNotExistException;
import com.phones.phones.model.Line;
import com.phones.phones.model.User;
import com.phones.phones.service.LineService;
import com.phones.phones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lines")
public class LineController {

    private final LineService lineService;
    private final SessionManager sessionManager;

    @Autowired
    public LineController(final LineService lineService, final SessionManager sessionManager) {
        this.lineService = lineService;
        this.sessionManager = sessionManager;
    }


    @PostMapping("/")
    public ResponseEntity createLine(@RequestHeader("Authorization") String sessionToken,
                                     @RequestBody @Valid final Line line) throws LineNumberAlreadyExistException, UserSessionNotExistException {
        User currentUser = sessionManager.getCurrentUser(sessionToken);
        if (currentUser.hasRoleEmployee()) {
            Line newLine = lineService.create(line);
            return ResponseEntity.created(getLocation(newLine)).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Line>> findAllLines(@RequestHeader("Authorization") String sessionToken) throws UserSessionNotExistException {
        User currentUser = sessionManager.getCurrentUser(sessionToken);
        if (currentUser.hasRoleEmployee()) {
            List<Line> lines = lineService.findAll();
            return (lines.size() > 0) ? ResponseEntity.ok(lines) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Line>> findLineById(@RequestHeader("Authorization") String sessionToken,
                                                       @PathVariable final Long id) throws LineNotExistException, UserSessionNotExistException {
        User currentUser = sessionManager.getCurrentUser(sessionToken);
        if (currentUser.hasRoleEmployee()) {
            return ResponseEntity.ok(lineService.findById(id));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLineById(@RequestHeader("Authorization") final String sessionToken,
                                         @PathVariable final Long id) throws LineNotExistException, UserSessionNotExistException {
        User currentUser = sessionManager.getCurrentUser(sessionToken);
        if (currentUser.hasRoleEmployee()) {
            int deleted = lineService.disableById(id);
            return (deleted > 0) ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateLineStatusById(@RequestHeader("Authorization") final String sessionToken,
                                               @RequestBody @Valid final LineStatusDto lineStatus,
                                               @PathVariable final Long id) throws LineNotExistException, UserSessionNotExistException {
        User currentUser = sessionManager.getCurrentUser(sessionToken);
        if (currentUser.hasRoleEmployee()) {
            Line line = lineService.updateLineStatusByIdLine(id, lineStatus);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    public ResponseEntity<List<Line>> findLinesByUserId(@RequestHeader("Authorization") final String sessionToken,
                                                        final Long id) throws UserSessionNotExistException, UserNotExistException {
        User currentUser = sessionManager.getCurrentUser(sessionToken);
        if (currentUser.hasRoleEmployee()) {
            List<Line> lines = lineService.findByUserId(id);
            return (lines.size() > 0) ? ResponseEntity.ok(lines) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    private URI getLocation(Line line) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(line.getId())
                .toUri();
    }

}
