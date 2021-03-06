package com.gopro.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopro.bene.NotificationUserMap;
import com.gopro.service.NotificationUserMapService;

@RestController
@RequestMapping("/notificationuser")
public class NotificationUserMapController {

	private NotificationUserMapService notificationUserMapService;
	
	@Autowired
	public NotificationUserMapController(NotificationUserMapService notificationUserMapService) {
		super();
		this.notificationUserMapService = notificationUserMapService;
	}

	@PostMapping(value ="/togglefav" )
	public NotificationUserMap toggleFav(@RequestBody NotificationUserMap notificationUserMap)
	{
		return notificationUserMapService.toggleFav(notificationUserMap);
	}
	
	@PostMapping(value="/deletemsg")
	public ResponseEntity<Boolean> deletMessage(@RequestBody Long messageId) {
		boolean isDeleted =  notificationUserMapService.deletMessage(messageId);
		return new ResponseEntity<>(isDeleted, OK);
	}
	
	@PostMapping(value="/updatereaded")
	public ResponseEntity<Boolean> updateReadMessageTrue(@RequestBody List<Long> notificationId) {
		boolean isUpdated =  notificationUserMapService.updateReadMessageFlag(notificationId , true);
		return new ResponseEntity<>(isUpdated, OK);
	}
	
	@PostMapping(value="/updateunread")
	public ResponseEntity<Boolean> updateReadMessageFalse(@RequestBody List<Long> notificationId) {
		boolean isUpdated =  notificationUserMapService.updateReadMessageFlag(notificationId , false);
		return new ResponseEntity<>(isUpdated, OK);
	}
	
}
