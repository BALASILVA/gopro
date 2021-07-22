package com.gopro.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

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

	@PostMapping(value="/getmessage")
	public ResponseEntity<List<NotificationMessageMap>> findAllMeesageOfNotificationId(@RequestBody Long notificationId) {
		//List<NotificationMessageMap> notificationMessageMapFromDb = notificationMessageMapService.findAllMeesageOfNotificationId(notificationId);
		//return new ResponseEntity<>(notificationMessageMapFromDb, OK);
		return null;
	}

	@PostMapping(value="/reply")
	public ResponseEntity<NotificationMessageMap> replyNotification(@RequestBody NotificationMessageMap notificationMessageMap) {
		NotificationMessageMap notificationMessageMapFromDb = notificationMessageMapService.addNewnotificationMessageMap(notificationMessageMap);				
		return new ResponseEntity<>(notificationMessageMapFromDb, OK);
	}
	
	//Delete all Messsage in SINGLE Thread
	@PostMapping(value="/deleteall")
	public ResponseEntity<Boolean> deleteAllMessage(@RequestBody Long notificationId) {
		boolean isDeleted = notificationMessageMapService.deleteAllMessage(notificationId);
		return new ResponseEntity<>(isDeleted, OK);
	}
	
	//Delete all Messsage in Multiple Thread
	@PostMapping(value="/deletebulk")
	public ResponseEntity<Boolean> deleteAllMessage(@RequestBody List<Long> notificationIdList) {
		boolean isDeleted = notificationMessageMapService.deleteAllMessage(notificationIdList);
		return new ResponseEntity<>(isDeleted, OK);
	}
	
}

