package com.gopro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopro.bene.InvoiceProductMap;
import com.gopro.repository.InvoiceProductRepo;

@Service
public class InvoiceProductServiceImpl implements InvoiceProductService {

	InvoiceProductRepo invoiceProductRepo;

	@Autowired
	public InvoiceProductServiceImpl(InvoiceProductRepo invoiceProductRepo) {
		this.invoiceProductRepo = invoiceProductRepo;
	}

	@Override
	public List<InvoiceProductMap> getInvoiceDetailByInvoiceId(Long invoiceId) {
		
		return null;
	}


	@Override
	public List<InvoiceProductMap> saveAll(List<InvoiceProductMap>  list) {
		return invoiceProductRepo.saveAll(list);
	}
}
