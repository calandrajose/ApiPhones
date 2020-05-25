package com.phones.phones.service;

import com.phones.phones.model.Call;
import com.phones.phones.repository.CallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CallService {

    private final CallRepository callRepository;

    @Autowired
    public CallService(final CallRepository callRepository) {
        this.callRepository = callRepository;
    }


    public void create(Call newCall) {
        callRepository.save(newCall);
    }

    public List<Call> findAll() {
        return callRepository.findAll();
    }

    public List<Call> findByUserId(Long id) {
        return callRepository.findAllByUserId(id);
    }

    public List<Call> findByUserIdBetweenDates(Long id, Date from, Date to) {
        return callRepository.findAllByUserIdBetweenDates(id, from, to);
    }
}
