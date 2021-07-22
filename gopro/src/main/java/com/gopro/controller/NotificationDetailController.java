package com.gopro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gopro.service.NotificationDetailService;


@RestController
@RequestMapping("/notificationdetail")
public class NotificationDetailController {

	private NotificationDetailService notificationDetailService;

	@Autowired
	public NotificationDetailController(NotificationDetailService notificationDetailService) {
		this.notificationDetailService = notificationDetailService;
	}
	
	@PostMapping(value = "/togglefavnotification")
	public void toogleFavNotification(@RequestBody Long notificationId)
	{
		this.notificationDetailService.toogleFavNotification(notificationId);
	}
	
	@PostMapping(value = "/addfavnotificationbulk")
	public void addFavNotificationInBulk(@RequestBody List<Long> notificationIdList)
	{
		this.notificationDetailService.makeFavNotificationInBulk(notificationIdList,true);
	}
	
	
	@PostMapping(value = "/removefavnotificationbulk")
	public void removeFavNotificationInBulk(@RequestBody List<Long> notificationIdList)
	{
		this.notificationDetailService.makeFavNotificationInBulk(notificationIdList,false);
	}
	
	@PostMapping(value = "/toggleimportant")
	public void toggleImportantNotification(@RequestBody Long notificationId)
	{
		this.notificationDetailService.toggleImportantNotification(notificationId);
	}
	
	@PostMapping(value = "/addimportantbulk")
	public void addImportantNotificationInBulk(@RequestBody List<Long> notificationIdList)
	{
		this.notificationDetailService.makeImportantNotificationInBulk(notificationIdList,true);
	}
	
	@PostMapping(value = "/removeimportantbulk")
	public void toggleImportantNotificationInBulk(@RequestBody List<Long> notificationIdList)
	{
		this.notificationDetailService.makeImportantNotificationInBulk(notificationIdList,false);
	}
	
}
