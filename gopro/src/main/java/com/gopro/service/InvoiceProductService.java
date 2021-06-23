package com.gopro.service;

import java.util.List;

import com.gopro.bene.Invoice;
import com.gopro.bene.InvoiceProductMap;

public interface InvoiceProductService {

	List<InvoiceProductMap> getInvoiceDetailByInvoiceId(Long invoiceId);

	List<InvoiceProductMap> saveAll(List<InvoiceProductMap> list);

	}
