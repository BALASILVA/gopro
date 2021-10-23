package com.gopro.controller;

import static org.springframework.http.HttpStatus.OK;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopro.bene.DashBoard;
import com.gopro.bene.SearchCredentialDTO;
import com.gopro.service.DashBoardService;

@RestController
@RequestMapping(path = { "/dashboard" })
public class DashBoardController {

	DashBoardService dashBoardService;
	
	Logger logger = LoggerFactory.getLogger(NotificationController.class);
	
	@Autowired
	public DashBoardController(DashBoardService dashBoardService) {
		this.dashBoardService = dashBoardService;
	}

	@PostMapping(value = "/todaysale")
	public	ResponseEntity<DashBoard> getDefaultShopTodaySales(SearchCredentialDTO searchCredentialDTO) {
		logger.info("/todaysale controller Start");
		DashBoard dashbord = dashBoardService.getDefaultShopTodaySales(searchCredentialDTO);
		logger.info("/todaysale controller Finshed");
    	return new ResponseEntity<DashBoard>(dashbord,OK);
	}

	@PostMapping(value = "/todaysale/all")
	public	ResponseEntity<DashBoard> getUserAllShopTodaySales(SearchCredentialDTO searchCredentialDTO) {
		logger.info("/todaysale/all controller Start");
		DashBoard dashbord = dashBoardService.getUserAllShopTodaySales(searchCredentialDTO);
		logger.info("/todaysale/all controller Finshed");
    	return new ResponseEntity<DashBoard>(dashbord,OK);
	}
	
	@PostMapping(value = "/salelist")
	public	ResponseEntity<DashBoard> getDefaultShopSalesList(SearchCredentialDTO searchCredentialDTO) {
		logger.info("/todaysale/all controller Start");
		DashBoard dashbord = dashBoardService.getDefaultShopSalesList(searchCredentialDTO);
		logger.info("/todaysale/all controller Finshed");
    	return new ResponseEntity<DashBoard>(dashbord,OK);
	}
	
	@PostMapping(value = "/salelist/all")
	public	ResponseEntity<DashBoard> getAllShopSalesList(SearchCredentialDTO searchCredentialDTO) {
		logger.info("/salelist/all controller Start");
		DashBoard dashbord = dashBoardService.getAllShopSalesList(searchCredentialDTO);
		logger.info("/salelist/all controller Finshed");
    	return new ResponseEntity<DashBoard>(dashbord,OK);
	}
}
