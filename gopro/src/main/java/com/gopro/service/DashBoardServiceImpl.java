package com.gopro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopro.bene.DashBoard;
import com.gopro.bene.SearchCredentialDTO;


@Service
public class DashBoardServiceImpl implements DashBoardService {

	InvoiceService invoiceService;

	@Autowired
	public DashBoardServiceImpl(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@Override
	public DashBoard getDefaultShopTodaySales(SearchCredentialDTO searchCredentialDTO) {
		return invoiceService.getDefaultShopTodaySales(searchCredentialDTO);
	}

	@Override
	public DashBoard getUserAllShopTodaySales(SearchCredentialDTO searchCredentialDTO) {
		return invoiceService.getUserAllShopTodaySales(searchCredentialDTO);
	}

	@Override
	public DashBoard getDefaultShopSalesList(SearchCredentialDTO searchCredentialDTO) {
		return invoiceService.getDefaultShopSalesList(searchCredentialDTO);
	}

	@Override
	public DashBoard getAllShopSalesList(SearchCredentialDTO searchCredentialDTO) {
		return invoiceService.getAllShopSalesList(searchCredentialDTO);
	}

}
