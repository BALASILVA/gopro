package com.gopro.service;

import org.springframework.data.domain.Page;

import com.gopro.bene.Invoice;
import com.gopro.bene.InvoiceProductMap;
import com.gopro.bene.SearchCredentialDTO;

public interface InvoiceService {

	Invoice add(Invoice invoice);

	Invoice getInvoiceDetailById(Invoice invoice);

	Page<Invoice> getAllInvoicePaginationAndSorting(SearchCredentialDTO searchCredentialDTO);

	Invoice sendReprintNotification(Invoice invoice);

}
