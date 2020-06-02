package com.phones.phones.service;

import com.phones.phones.exception.user.UserNotExistException;
import com.phones.phones.model.Call;
import com.phones.phones.model.User;
import com.phones.phones.repository.CallRepository;
import com.phones.phones.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CallService {

    private final CallRepository callRepository;
    private final UserRepository userRepository;

    @Autowired
    public CallService(final CallRepository callRepository,
                       final UserRepository userRepository) {
        this.callRepository = callRepository;
        this.userRepository = userRepository;
    }


    public void create(Call newCall) {
        callRepository.save(newCall);
    }

    public List<Call> findAll() {
        return callRepository.findAll();
    }

    public List<Call> findByUserId(Long id) throws UserNotExistException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotExistException();
        }
        return callRepository.findAllByUserId(id);
    }

    public List<Call> findByUserIdBetweenDates(Long id,
                                               Date from,
                                               Date to) throws UserNotExistException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotExistException();
        }
        return callRepository.findAllByUserIdBetweenDates(id, from, to);
    }

    public String findMostCalledByOriginId(Long id){
        return  callRepository.findMostCalledByOriginId(id);
    }

}
