package com.gopro.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gopro.AuthendicationFacade.AuthendicationFacade;
import com.gopro.bene.Invoice;
import com.gopro.bene.Notification;
import com.gopro.bene.NotificationMessageMap;
import com.gopro.bene.NotificationUserMap;
import com.gopro.bene.SearchCredentialDTO;
import com.gopro.bene.User;
import com.gopro.repository.NotificationRepo;
import com.gopro.service.NotificationService;
import static com.gopro.constant.NotificationConstant.*;
import static com.gopro.constant.CommanConstants.*;

@Service
public class NotificationServiceImpl implements NotificationService {

	private AuthendicationFacade authendicationFacade;
	private NotificationRepo notificationRepo;
	private UserService userService;

	@Autowired
	public NotificationServiceImpl(NotificationRepo notificationRepo, UserService userService,
			AuthendicationFacade authendicationFacade) {
		this.notificationRepo = notificationRepo;
		this.userService = userService;
		this.authendicationFacade = authendicationFacade;
	}

	@Override
	public boolean getUpdateAllNotification(SearchCredentialDTO searchCredentialDTO) {
		return true;
	}

	// Compose New Mail
	@Override
	public Notification addNewMessageNotification(Notification notification) {				
		User logedInUser = authendicationFacade.getCurrentUserDetails();		

		// set notification detail
		notification.setInvoiceId(LONG_ZERO);
		notification.setShopId(NULL);
		notification.setSystemGenerated(false);
		notification.setNotificationType(NOTIFICATION_TYPE_COMPOSE_NEW);
		notification.setNotificationStartData(new Date());

		notification.setNotificationLatUpdateDate(new Date());

		// Set message details
		List<NotificationMessageMap> notificationMessageMapList = notification.getNotificationMessageMap();
		for (NotificationMessageMap messageObj : notificationMessageMapList) {
			if (messageObj.getNotificationMessageMapId() == NULL) {
				messageObj.setSendFrom(logedInUser.getId());
				messageObj.setTime(new Date());
			}
		}

		return notificationRepo.save(notification);
	}

	// Search Page
	@Override
	public List<Notification> getAllNotification(SearchCredentialDTO searchCredentialDTO) {
		User logedInUser = authendicationFacade.getCurrentUserDetails();
		System.out.println(searchCredentialDTO);
		Pageable pageable = null;

		if (searchCredentialDTO.getSize() == 0 || searchCredentialDTO.getSize() <= 0) {
			searchCredentialDTO.setSize(100);
		}
		pageable = PageRequest.of(searchCredentialDTO.getPage(), searchCredentialDTO.getSize());

		if (StringUtils.isEmpty(searchCredentialDTO.getSearchKeyWord())) {
			searchCredentialDTO.setSearchKeyWord("");
		}

		searchCredentialDTO.setSearchKeyWord("%" + searchCredentialDTO.getSearchKeyWord() + "%");

		try {

			boolean isDataUpdateAfterSearch = true;
			System.out.println(searchCredentialDTO.getNotificationLatUpdateDate());
			if (searchCredentialDTO.getNotificationLatUpdateDate() != null) {
				Integer count = notificationRepo.getUpdateAllNotification(logedInUser.getId(),
						searchCredentialDTO.getSendFrom(), searchCredentialDTO.getSendTo(),
						searchCredentialDTO.getFromDate(), searchCredentialDTO.getToDate(),
						searchCredentialDTO.isFavorite(), searchCredentialDTO.isReaded(),
						searchCredentialDTO.getSearchKeyWord(), searchCredentialDTO.getNotificationLatUpdateDate());
				System.out.println("CHEING UPDATE " + count);
				System.out.println("CHEING UPDATE " + (Integer) count);
				if ((Integer) count > 0)
					isDataUpdateAfterSearch = true;
				else
					isDataUpdateAfterSearch = false;
			}

			if (isDataUpdateAfterSearch)
				return notificationRepo.getAllNotifications(logedInUser.getId(), searchCredentialDTO.getSendFrom(),
						searchCredentialDTO.getSendTo(), searchCredentialDTO.getFromDate(),
						searchCredentialDTO.getToDate(), searchCredentialDTO.isFavorite(),
						searchCredentialDTO.isReaded(), searchCredentialDTO.getSearchKeyWord(), pageable);
			else
				return null;

		} catch (Exception ex) {
			System.out.println("Exception" + ex);
			return null;
		}
	}

	@Override
	public Notification findNotificationByInvoiceId(Long invoiceId) {
		return notificationRepo.findNotificationByInvoiceId(invoiceId);
	}

	@Override
	public Notification sendReprintNotification(Invoice invoice) {

		User logedInUser = authendicationFacade.getCurrentUserDetails();
		Notification notification = findNotificationByInvoiceId(invoice.getInvoiceId());
		if (notification == null || notification.getNotificationId() == null) {
			notification = new Notification();
			// set notification detail
			notification.setInvoiceId(invoice.getInvoiceId());
			notification.setShopId(logedInUser.getDefaultShopId());
			notification.setSubject("Invoice Id " + invoice.getInvoiceId() + " is reprinted");
			notification.setDeleted(false);
			notification.setSystemGenerated(true);
			notification.setNotificationType(NOTIFICATION_TYPE_REPRINT);
			notification.setNotificationStartData(new Date());
		}

		notification.setNotificationLatUpdateDate(new Date());
		// Set message details
		List<NotificationMessageMap> notificationMessageMapList = notification.getNotificationMessageMap();
		NotificationMessageMap notificationMessageMap = new NotificationMessageMap();
		notificationMessageMap.setSendFrom(logedInUser.getId());
		notificationMessageMap
				.setMessage("Inoice Id " + invoice.getInvoiceId() + " is reprinted by " + logedInUser.getUserId());
		notificationMessageMap.setTime(new Date());
		notificationMessageMapList.add(notificationMessageMap);

		// Need to create new Notification to all Higer Role
		List<User> users = userService.getUserForReprintNotification(logedInUser);
		List<NotificationUserMap> notificationUserMapList = new ArrayList<NotificationUserMap>();
		for (User user : users) {
			NotificationUserMap notificationUserMap = new NotificationUserMap();
			notificationUserMap.setSendTo(user.getId());
			notificationUserMap.setFavorite(false);
			notificationUserMap.setMappingType(NOTIFICATION_TYPE_REPRINT);
			notificationUserMap.setReaded(false);
			notificationUserMap.setDeleted(false);
			notificationUserMapList.add(notificationUserMap);
		}
		notificationMessageMap.setNotificationUserMap(notificationUserMapList);
		notification.setNotificationMessageMap(notificationMessageMapList);
		notificationRepo.save(notification);

		return notification;
	}

	@Override
	public boolean updateLastUpdateTimeDate(Long notificationId) {
		if(notificationId == null || notificationId==0)
			return false;		
		int count = notificationRepo.updateLastUpdateTimeDate(new Date(),notificationId);
		System.out.println("Time Update"+count);
		return count>0;
	}

}
