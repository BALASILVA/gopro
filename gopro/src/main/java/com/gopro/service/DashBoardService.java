package com.gopro.service;

import com.gopro.bene.DashBoard;
import com.gopro.bene.SearchCredentialDTO;

public interface DashBoardService {

	DashBoard getDefaultShopTodaySales(SearchCredentialDTO searchCredentialDTO);

	DashBoard getUserAllShopTodaySales(SearchCredentialDTO searchCredentialDTO);

	DashBoard getDefaultShopSalesList(SearchCredentialDTO searchCredentialDTO);

	DashBoard getAllShopSalesList(SearchCredentialDTO searchCredentialDTO);

}
