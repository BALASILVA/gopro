package com.gopro.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopro.bene.Invoice;
import com.gopro.bene.InvoiceProductMap;
import com.gopro.service.InvoiceProductService;

@RestController
@RequestMapping(value = "/invoiceddetail")
public class InvoiceDetailController {

	InvoiceProductService invoiceProductService;

	public InvoiceDetailController(InvoiceProductService invoiceProductService) {
		super();
		this.invoiceProductService = invoiceProductService;
	}

	@PostMapping
	public ResponseEntity<List<InvoiceProductMap>> addInvoice(@RequestBody Invoice invoice) {
		List<InvoiceProductMap> invoiceDetail = invoiceProductService.getInvoiceDetailByInvoiceId(invoice.getInvoiceId());
		return new ResponseEntity<>(invoiceDetail, OK);
	}

}
