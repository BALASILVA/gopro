package com.gopro.service;

import static com.gopro.constant.CommanConstants.LONG_ZERO;
import static com.gopro.constant.CommanConstants.NULL;
import static com.gopro.constant.NotificationConstant.NOTIFICATION_TYPE_COMPOSE_NEW;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopro.AuthendicationFacade.AuthendicationFacade;
import com.gopro.bene.NotificationMessageMap;
import com.gopro.bene.User;
import com.gopro.repository.NotificationMessageMapRepo;

@Service
public class NotificationMessageMapServiceImpl implements NotificationMessageMapService {

	private NotificationMessageMapRepo notificationMessageMapRepo;
	private NotificationService notificationService; 	
	private UserService userService;
	private AuthendicationFacade authendicationFacade;
	
	@Autowired
	public NotificationMessageMapServiceImpl(NotificationMessageMapRepo notificationMessageMapRepo,UserNewMailMappingService userNewMailMappingService,NotificationService notificationService,UserService userService,AuthendicationFacade authendicationFacade) {
		this.notificationMessageMapRepo = notificationMessageMapRepo;
		this.authendicationFacade = authendicationFacade;
		this.notificationService = notificationService;
		this.userService = userService;		
	}


	@Override
	public NotificationMessageMap addNewnotificationMessageMap(NotificationMessageMap notificationMessageMap) {
		
		if(notificationMessageMap.getNotificationId() == null)
		{
			//Exception To Be Handled			
			return null;
		}
		
		User logedInUser = authendicationFacade.getCurrentUserDetails();										

		notificationMessageMap.setSendFrom(logedInUser.getId());		
		notificationMessageMap.setTime(new Date());			
		notificationMessageMap.setReplyOrReplyAllAction(true);
		NotificationMessageMap notificationMessageMapSaved  = notificationMessageMapRepo.save(notificationMessageMap);	
				
		List<Long> userIdList = new ArrayList<Long>();
		for (int i = 0; i < notificationMessageMap.getNotificationUserMap().size(); i++) 
			userIdList.add(notificationMessageMap.getNotificationUserMap().get(i).getSendTo());				
		//Remove duplicate
		Set<Long> set = new HashSet<>(userIdList);
		userIdList.clear();
		userIdList.addAll(set);
		
		boolean updateResult = notificationService.updateLastUpdateTimeDate(notificationMessageMap.getNotificationId());
		boolean isNewMessageStatusUpdated = userService.updateNewMailTrue(userIdList);		
								
		
		return notificationMessageMapSaved;
	}

}
