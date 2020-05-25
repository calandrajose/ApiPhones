package com.phones.phones.controller;

import com.phones.phones.model.Invoice;
import com.phones.phones.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(final InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    //@PostMapping("/")
    public void createInvoice(@RequestBody @Valid final Invoice invoice) {
        invoiceService.create(invoice);
    }

    @GetMapping("/")
    public List<Invoice> findAllInvoices() {
        return invoiceService.findAll();
    }

}
