package com.phones.phones.service;

import com.phones.phones.model.Invoice;
import com.phones.phones.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(final InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }


    public void create(Invoice newInvoice) {
        invoiceRepository.save(newInvoice);
    }

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

}
