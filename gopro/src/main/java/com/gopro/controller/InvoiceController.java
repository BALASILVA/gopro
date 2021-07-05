package com.gopro.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopro.bene.Invoice;
import com.gopro.bene.InvoiceProductMap;
import com.gopro.bene.SearchCredentialDTO;
import com.gopro.exception.domain.EmailExistException;
import com.gopro.exception.domain.UserNotFoundException;
import com.gopro.exception.domain.UsernameExistException;
import com.gopro.service.InvoiceService;

@RestController
@RequestMapping(path = { "/invoice" })
public class InvoiceController {

	private InvoiceService invoiceService;

	@Autowired
	public InvoiceController(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@GetMapping
	public Invoice getInvoice() {
		System.out.println("in");
		Invoice invoice = new Invoice();
		System.out.println("email");
		return invoice;
	}

	@PostMapping(value="add")
	public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice) {
		System.out.println("invoice" + invoice);
		Invoice savedInvoice = invoiceService.add(invoice);
		return new ResponseEntity<>(savedInvoice, OK);
	}
	
	@PostMapping(value="reprint")
	public ResponseEntity<Invoice> reAddInvoice(@RequestBody Invoice invoice) {
		System.out.println("reprinting ");
		Invoice updatedInvoice  = invoiceService.sendReprintNotification(invoice);
		return new ResponseEntity<>(updatedInvoice, OK);
	}
	
	@PostMapping(value="/details")
	public ResponseEntity<Invoice> getInvoiceDetail(@RequestBody Invoice invoice) {
		Invoice invoiceDetail = invoiceService.getInvoiceDetailById(invoice);
		return new ResponseEntity<>(invoiceDetail, OK);
	}

	@PostMapping(value="/all")
	public ResponseEntity<Page<Invoice>> getAllInvoiceByShopId(@RequestBody SearchCredentialDTO searchCredentialDTO)
			throws UserNotFoundException, UsernameExistException, EmailExistException {
		Page<Invoice> invoiceList = invoiceService.getAllInvoicePaginationAndSorting(searchCredentialDTO);
		
		System.out.println(invoiceList);
		return new ResponseEntity<>(invoiceList, OK);
	}	
}
