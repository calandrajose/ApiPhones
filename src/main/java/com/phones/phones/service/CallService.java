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
    public CallService(CallRepository callRepository) {
        this.callRepository = callRepository;
    }


    public void add(Call newCall) {
        callRepository.save(newCall);
    }

    public List<Call> getAll() {
        return callRepository.findAll();
    }

    public List<Call> getByUserId(Long id) {
        return callRepository.findAllByUserId(id);
    }

    public List<Call> getByUserIdBetweenDates(Long id, Date from, Date to) {
        return callRepository.findAllByUserIdBetweenDates(id, from, to);
    }
}
