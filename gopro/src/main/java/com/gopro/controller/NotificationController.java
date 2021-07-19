package com.gopro.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopro.bene.Notification;
import com.gopro.bene.SearchCredentialDTO;
import com.gopro.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	private NotificationService notificationService;
	
	@Autowired
	public NotificationController(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	
	@PostMapping(value="/compose/new")
	public ResponseEntity<Notification> sendNotification(@RequestBody Notification notification) {					
		Notification notifications = notificationService.addNewMessageNotification(notification);				
		return new ResponseEntity<>(notifications, OK);
	}
	
	@PostMapping(value="/forward")
	public ResponseEntity<Notification> forwardNotification(@RequestBody Notification notification) {					
		//Notification notifications = notificationService.addNewMessageNotification(notification);				
		return new ResponseEntity<>(null, OK);
	}	

	@PostMapping(value="/all",produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Page<Notification>> getAllNotification(@RequestBody SearchCredentialDTO searchCredentialDTO) {		
		Page<Notification> notifications = notificationService.getAllNotification(searchCredentialDTO);				
		return new ResponseEntity<>(notifications, OK);		
	}
	
	@PostMapping(value="/all/refresh")
	public boolean getUpdateAllNotification(@RequestBody SearchCredentialDTO searchCredentialDTO) {
		return notificationService.getUpdateAllNotification(searchCredentialDTO);
	}
	
}
