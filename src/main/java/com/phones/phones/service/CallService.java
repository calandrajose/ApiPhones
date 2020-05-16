package com.phones.phones.service;

import com.phones.phones.model.Call;
import com.phones.phones.repository.CallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallService {

    private final CallRepository callRepository;

    @Autowired
    public CallService(CallRepository callRepository) {
        this.callRepository = callRepository;
    }


    public void add(Call call) {
        callRepository.save(call);
    }

    public List<Call> getAll() {
        return callRepository.findAll();
    }

    /*
    public List<Call> getAllByUserId(Long id) {
        return callRepository.
    }*/
}
