package com.phones.phones.service;

import com.phones.phones.exception.user.UserNotExistException;
import com.phones.phones.model.Invoice;
import com.phones.phones.model.User;
import com.phones.phones.repository.InvoiceRepository;
import com.phones.phones.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;

    @Autowired
    public InvoiceService(final InvoiceRepository invoiceRepository,
                          final UserRepository userRepository) {
        this.invoiceRepository = invoiceRepository;
        this.userRepository = userRepository;
    }


    public void create(Invoice newInvoice) {
        invoiceRepository.save(newInvoice);
    }

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> findById(Long id) {
        return invoiceRepository.findById(id);
    }

    public List<Invoice> findByUserId(Long id) throws UserNotExistException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotExistException();
        }
        return invoiceRepository.findByUserId(id);
    }

    public List<Invoice> findByUserIdBetweenDates(Long id,
                                                  Date from,
                                                  Date to) throws UserNotExistException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotExistException();
        }
        return invoiceRepository.findByUserIdBetweenDates(id, from, to);
    }

}
