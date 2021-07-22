package com.gopro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gopro.AuthendicationFacade.AuthendicationFacade;
import com.gopro.bene.NotificationMessageMap;
import com.gopro.bene.NotificationUserMap;
import com.gopro.bene.User;
import com.gopro.constant.NotificationConstant;
import com.gopro.repository.NotificationUserMapRepo;

@Service
public class NotificationUserMapServiceImpl implements NotificationUserMapService {

	private NotificationUserMapRepo notificationUserMapRepo;
	private NotificationMessageMapService notificationMessageMapService;
	private AuthendicationFacade authendicationFacade;
	@Autowired
	public NotificationUserMapServiceImpl(NotificationUserMapRepo notificationUserMapRepo,
			@Lazy NotificationMessageMapService notificationMessageMapService,
			AuthendicationFacade authendicationFacade) {
		this.notificationUserMapRepo = notificationUserMapRepo;
		this.notificationMessageMapService = notificationMessageMapService;
		this.authendicationFacade = authendicationFacade;
	}

	@Override
	public List<NotificationUserMap> findAllMapedUserByMeesageMapId(Long notificationMessageMapId) {
		return notificationUserMapRepo.findAllMapedUserByMeesageMapId(notificationMessageMapId);
	}

	@Override
	public boolean updateUserMapForReplyAction(List<Long> userId , NotificationMessageMap notificationMessageMap) {

		//All Message send previously is mapped with new user Handle in query.
		try {
		Long parentNotificationMessageMapId = notificationMessageMap.getParentNotificationMessageMapId();
		List<NotificationUserMap> userOfThisMessage = notificationMessageMap.getNotificationUserMap();		
		List<NotificationUserMap> userOfParentMessage = findAllMapedUserByMeesageMapId(parentNotificationMessageMapId);		
		
		List<NotificationUserMap> listOfFilterUser = new ArrayList<NotificationUserMap>();		
		//Filter new user
		
		for (NotificationUserMap thisUser : userOfThisMessage) {
			boolean isExist = false;
			for (NotificationUserMap parentUser : userOfParentMessage) {
				if(parentUser.getSendTo() == thisUser.getSendTo()){
					isExist = true;
				}
			}
			if(!isExist)
				listOfFilterUser.add(thisUser);
		}
		
		System.out.println("new user add in reply all");
		System.out.println(listOfFilterUser.toString());
		
		//Mapping (Old/Previous) Message to newly added User in reply all action
		List<NotificationUserMap> listOfUserMapToSave = new ArrayList<NotificationUserMap>();
		if(listOfFilterUser.size() != 0)
		{
			//List<NotificationMessageMap> notificationMessageId = notificationMessageMapService.findAllMessageIdFromNotificationId(notificationMessageMap.getNotificationId());
			System.out.println("try to det parent msg id"+notificationMessageMap.getNotificationMessageMapId());
			List<NotificationMessageMap> notificationMessageId = notificationMessageMapService.findAllParentMessageIdOfMessageId(notificationMessageMap.getNotificationMessageMapId());
			System.out.println(notificationMessageId.toString());
			for (NotificationUserMap user : listOfFilterUser) {
				for (NotificationMessageMap messageobj : notificationMessageId) {	
					NotificationUserMap notificationUserMap = new NotificationUserMap();
					notificationUserMap.setSendTo(user.getSendTo());					
					notificationUserMap.setNotificationMessageMapId(messageobj.getNotificationMessageMapId());
					notificationUserMap.setIsFavorite(false);
					notificationUserMap.setIsReaded(false);
					notificationUserMap.setDeleted(false);					
					notificationUserMap.setMappingType(NotificationConstant.NOTIFICATION_MAPPING_TYPE_REPLYALL_REFERENCE);
					listOfUserMapToSave.add(notificationUserMap);
				}
			}			
			System.out.println("try to save");
			for (NotificationUserMap userMap : listOfUserMapToSave) {
				notificationUserMapRepo.saveIfNotExits(userMap.getSendTo(),userMap.getMappingType(),userMap.getNotificationMessageMapId());
			}		
			//notificationUserMapRepo.saveAll(listOfUserMapToSave);
		}		
		
		}
		catch (Exception ex) {
			//Exception TO be handle
			System.out.println("exception"+ex);
			return false;
		}
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public NotificationUserMap toggleFav(NotificationUserMap notificationUserMap) {
		User logedInUser = authendicationFacade.getCurrentUserDetails();
		System.out.println(notificationUserMap.getIsFavorite() +" "+ logedInUser.getId()+" "+notificationUserMap.getNotificationMessageMapId());
		notificationUserMapRepo.toggleFav(notificationUserMap.getIsFavorite(), logedInUser.getId(),notificationUserMap.getNotificationMessageMapId());
		return notificationUserMap;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean updateReadedFlag(List<Long> notificationMessageIdList, Long userId) {
		int count = notificationUserMapRepo.updateReadedFlag(userId,notificationMessageIdList);
		return count>0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean deletMessage(Long messageId) {
		User logedInUser = authendicationFacade.getCurrentUserDetails();
		int count = notificationUserMapRepo.deletMessage(logedInUser.getId(),messageId);
		System.out.println(count);
		return true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean updateReadMessageFlag(List<Long> notificationId, boolean isReaded) {
		User logedInUser = authendicationFacade.getCurrentUserDetails();
		int count = notificationUserMapRepo.updateReadMessageFlag(notificationId,logedInUser.getId(),isReaded);
		return true;
	}

}
