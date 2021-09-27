package com.gopro.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopro.bene.DashBoard;
import com.gopro.bene.Invoice;
import com.gopro.bene.SearchCredentialDTO;
import com.gopro.service.DashBoardService;

@RestController
@RequestMapping(path = { "/dashboard" })
public class DashBoardController {

	DashBoardService dashBoardService;
	
	
	@Autowired
	public DashBoardController(DashBoardService dashBoardService) {
		this.dashBoardService = dashBoardService;
	}

	@PostMapping(value = "/todaysale")
	public	ResponseEntity<DashBoard> getDefaultShopTodaySales(SearchCredentialDTO searchCredentialDTO) {
		DashBoard dashbord = dashBoardService.getDefaultShopTodaySales(searchCredentialDTO);
    	return new ResponseEntity<DashBoard>(dashbord,OK);
	}

	@PostMapping(value = "/todaysale/all")
	public	ResponseEntity<DashBoard> getUserAllShopTodaySales(SearchCredentialDTO searchCredentialDTO) {
		DashBoard dashbord = dashBoardService.getUserAllShopTodaySales(searchCredentialDTO);
    	return new ResponseEntity<DashBoard>(dashbord,OK);
	}
	
	@PostMapping(value = "/salelist")
	public	ResponseEntity<DashBoard> getDefaultShopSalesList(SearchCredentialDTO searchCredentialDTO) {
		DashBoard dashbord = dashBoardService.getDefaultShopSalesList(searchCredentialDTO);
    	return new ResponseEntity<DashBoard>(dashbord,OK);
	}
	
	@PostMapping(value = "/salelist/all")
	public	ResponseEntity<DashBoard> getAllShopSalesList(SearchCredentialDTO searchCredentialDTO) {
		DashBoard dashbord = dashBoardService.getAllShopSalesList(searchCredentialDTO);
    	return new ResponseEntity<DashBoard>(dashbord,OK);
	}
}
