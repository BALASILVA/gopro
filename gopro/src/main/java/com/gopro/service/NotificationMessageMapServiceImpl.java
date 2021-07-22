package com.gopro.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gopro.AuthendicationFacade.AuthendicationFacade;
import com.gopro.bene.Notification;
import com.gopro.bene.NotificationMessageMap;
import com.gopro.bene.NotificationUserMap;
import com.gopro.bene.User;
import com.gopro.constant.NotificationConstant;
import com.gopro.repository.NotificationMessageMapRepo;

@Service
public class NotificationMessageMapServiceImpl implements NotificationMessageMapService {

	private NotificationMessageMapRepo notificationMessageMapRepo;
	private NotificationService notificationService;
	private UserService userService;
	private AuthendicationFacade authendicationFacade;
	private NotificationUserMapService notificationUserMapService;
	private NotificationDetailService notificationDetailService;
	
	@Autowired
	public NotificationMessageMapServiceImpl(NotificationMessageMapRepo notificationMessageMapRepo,
			UserNewMailMappingService userNewMailMappingService, NotificationService notificationService,
			UserService userService, AuthendicationFacade authendicationFacade,
			NotificationUserMapService notificationUserMapService,
			 NotificationDetailService notificationDetailService) {
		this.notificationMessageMapRepo = notificationMessageMapRepo;
		this.authendicationFacade = authendicationFacade;
		this.notificationService = notificationService;
		this.userService = userService;
		this.notificationUserMapService = notificationUserMapService;
		this.notificationDetailService = notificationDetailService;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public NotificationMessageMap addNewnotificationMessageMap(NotificationMessageMap notificationMessageMap) {

		if (notificationMessageMap.getNotificationId() == null) {
			// Exception To Be Handled
			return null;
		}
		Long parentMessageId = notificationMessageMap.getParentNotificationMessageMapId();
		
		User logedInUser = authendicationFacade.getCurrentUserDetails();				
		
		NotificationUserMap notificationUserMapObj = new NotificationUserMap();		
		notificationUserMapObj.setSendTo(logedInUser.getId());
		notificationUserMapObj.setMappingType(NotificationConstant.NOTIFICATION_MAPPING_TYPE_FROM);
		notificationMessageMap.getNotificationUserMap().add(notificationUserMapObj);
		
		notificationMessageMap.setSendFrom(logedInUser.getId());
		notificationMessageMap.setTime(new Date());
		notificationMessageMap.setReplyOrReplyAllAction(true); 		
		NotificationMessageMap notificationMessageMapSaved = notificationMessageMapRepo.save(notificationMessageMap);		
		
		List<Long> userIdList = new ArrayList<Long>();
		for (int i = 0; i < notificationMessageMap.getNotificationUserMap().size(); i++)
			userIdList.add(notificationMessageMap.getNotificationUserMap().get(i).getSendTo());
		
		// Remove duplicate
		Set<Long> set = new HashSet<>(userIdList);
		userIdList.clear();
		userIdList.addAll(set);
	
		//Below function will map the old message to new added user in this loop
		boolean userMapingResult = notificationUserMapService.updateUserMapForReplyAction(userIdList,notificationMessageMap);
		//Update Notification Detail.  This function add newly added user in NotificationDetail  table
		boolean notificationDetailUpdate = notificationDetailService.updateNotificationDetail(userIdList, notificationMessageMap.getNotificationId());
		boolean updateResult = notificationService.updateLastUpdateTimeDate(notificationMessageMap.getNotificationId());
		boolean isNewMessageStatusUpdated = userService.updateNewMailTrue(userIdList);

		return findDetailedMeesageOfNotificationMessageId(logedInUser.getId(),notificationMessageMapSaved);
	}

	public NotificationMessageMap findDetailedMeesageOfNotificationMessageId(Long userId , NotificationMessageMap notificationMessage) {
		
		NotificationMessageMap notificationMessageMapFromDb = notificationMessageMapRepo.findMessageByMessageId(userId,notificationMessage.getNotificationMessageMapId()); 
		
		List<NotificationUserMap> notificationUserMap = notificationUserMapService.findAllMapedUserByMeesageMapId(notificationMessageMapFromDb.getNotificationMessageMapId());
		
		notificationMessageMapFromDb.setNotificationUserMap(notificationUserMap);
		
		return notificationMessageMapFromDb;
		
	}
	@Override
	public List<NotificationMessageMap> findLastMeesageOfNotificationId(Notification notification, User logInUser) {
		List<NotificationMessageMap> notificationMessageMap = notificationMessageMapRepo
				.findLastMeesageOfNotificationId(notification.getNotificationId(), logInUser.getId());
		for(NotificationMessageMap notificationMessage:notificationMessageMap)
		{
			List<NotificationUserMap> notificationUserMap = notificationUserMapService.findAllMapedUserByMeesageMapId(notificationMessage.getNotificationMessageMapId());
			notificationMessage.setNotificationUserMap(notificationUserMap);
		}
		
		return notificationMessageMap;
	}

	@Override
	public List<NotificationMessageMap> findAllMeesageOfNotificationId(Long userId , Long notificationId) {
						
		List<NotificationMessageMap> notificationMessageMap = notificationMessageMapRepo
				.findAllMeesageOfNotificationId(notificationId, userId);
		List<Long> notificationMessageIdList = new ArrayList<Long>();
		
		for (NotificationMessageMap notificationMessage : notificationMessageMap) {
			List<NotificationUserMap> notificationUserMap = notificationUserMapService.findAllMapedUserByMeesageMapId(notificationMessage.getNotificationMessageMapId());
			notificationMessage.setNotificationUserMap(notificationUserMap);
			notificationMessageIdList.add(notificationMessage.getNotificationMessageMapId());
		}
		updateReadedFlag(notificationMessageIdList,userId);
		return notificationMessageMap;
	}	
	

	public boolean updateReadedFlag(List<Long> notificationMessageIdList,Long userId)
	{
		notificationUserMapService.updateReadedFlag(notificationMessageIdList,userId);
		return true;
	}
	
	@Override
	public List<NotificationMessageMap> findAllMessageIdFromNotificationId(Long notificationId) {
		return notificationMessageMapRepo
				.findAllMessageIdOfNotificationId(notificationId);
	}

	
	@Override
	public List<NotificationMessageMap> findAllParentMessageIdOfMessageId(Long notificationMessageMapId) {
		return notificationMessageMapRepo
				.findAllParentMessageIdOfMessageId(notificationMessageMapId);
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean deleteAllMessage(Long notificationId) {		
		User logedInUser = authendicationFacade.getCurrentUserDetails();		
		notificationMessageMapRepo
				.deleteAllMessage(notificationId,logedInUser.getId());		
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean deleteAllMessage(List<Long> notificationIdList) {
		User logedInUser = authendicationFacade.getCurrentUserDetails();		
		notificationMessageMapRepo
				.deleteAllMessageBulk(notificationIdList,logedInUser.getId());		
		return true;
	}


}
