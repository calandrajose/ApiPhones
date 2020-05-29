package com.phones.phones.controller;

import com.phones.phones.dto.RateDto;
import com.phones.phones.exception.user.UserSessionNotExistException;
import com.phones.phones.model.User;
import com.phones.phones.service.RateService;
import com.phones.phones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rates")
public class RateController {

    private final RateService rateService;
    private final SessionManager sessionManager;

    @Autowired
    public RateController(final RateService rateService,
                          final SessionManager sessionManager) {
        this.rateService = rateService;
        this.sessionManager = sessionManager;
    }


    @GetMapping("/")
    public ResponseEntity<List<RateDto>> findAllRates(@RequestHeader("Authorization") final String sessionToken) throws UserSessionNotExistException {
        User currentUser = sessionManager.getCurrentUser(sessionToken);
        if (currentUser.hasRoleEmployee()) {
            List<RateDto> rates = rateService.findAll();
            return (rates.size() > 0) ? ResponseEntity.ok(rates) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
