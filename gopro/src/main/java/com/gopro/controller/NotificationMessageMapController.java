package com.gopro.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopro.bene.NotificationMessageMap;
import com.gopro.service.NotificationMessageMapService;

@RestController
@RequestMapping("/message")
public class NotificationMessageMapController {
	
	private NotificationMessageMapService notificationMessageMapService;
	
	
	@Autowired
	public NotificationMessageMapController(NotificationMessageMapService notificationMessageMapService) {
		super();
		this.notificationMessageMapService = notificationMessageMapService;
	}



	@PostMapping(value="/reply")
	public ResponseEntity<NotificationMessageMap> replyNotification(@RequestBody NotificationMessageMap notificationMessageMap) {
		NotificationMessageMap notificationMessageMapFromDb = notificationMessageMapService.addNewnotificationMessageMap(notificationMessageMap);				
		return new ResponseEntity<>(notificationMessageMapFromDb, OK);
	}
}