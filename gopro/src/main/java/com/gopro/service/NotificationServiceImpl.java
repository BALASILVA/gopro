package com.gopro.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gopro.AuthendicationFacade.AuthendicationFacade;
import com.gopro.bene.Invoice;
import com.gopro.bene.Notification;
import com.gopro.bene.NotificationDetail;
import com.gopro.bene.NotificationMessageMap;
import com.gopro.bene.NotificationUserMap;
import com.gopro.bene.SearchCredentialDTO;
import com.gopro.bene.User;
import com.gopro.constant.NotificationConstant;
import com.gopro.repository.NotificationRepo;
import com.gopro.service.NotificationService;
import static com.gopro.constant.NotificationConstant.*;
import static com.gopro.constant.CommanConstants.*;

@Service
public class NotificationServiceImpl implements NotificationService {

	private AuthendicationFacade authendicationFacade;
	private NotificationRepo notificationRepo;
	private UserService userService;
	private NotificationMessageMapService notificationMessageMapService;
	private NotificationDetailService notificationDetailService;
	private CommanService commanService;

	@Autowired
	public NotificationServiceImpl(NotificationRepo notificationRepo, UserService userService,
			AuthendicationFacade authendicationFacade,
			@Lazy NotificationMessageMapService notificationMessageMapService,
			NotificationDetailService notificationDetailService,
			CommanService commanService) {
		this.notificationRepo = notificationRepo;
		this.userService = userService;
		this.authendicationFacade = authendicationFacade;
		this.notificationMessageMapService = notificationMessageMapService;
		this.notificationDetailService = notificationDetailService;
		this.commanService = commanService;
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
		
		Set<Long> userIdToThisMail = new HashSet<Long>();
		List<Long> userIdList = new ArrayList<Long>();
		
		// Set message details
		List<NotificationMessageMap> notificationMessageMapList = notification.getNotificationMessageMap();
		for (NotificationMessageMap messageObj : notificationMessageMapList) {
			if (messageObj.getNotificationMessageMapId() == NULL) {
				messageObj.setParentNotificationMessageMapId(LONG_ZERO);
				messageObj.setSendFrom(logedInUser.getId());
				messageObj.setTime(new Date());				
			}
			NotificationUserMap notificationUserMapObj = new NotificationUserMap();
			notificationUserMapObj.setSendTo(logedInUser.getId());
			notificationUserMapObj.setMappingType(NotificationConstant.NOTIFICATION_MAPPING_TYPE_FROM);
			messageObj.getNotificationUserMap().add(notificationUserMapObj);
			
			for(NotificationUserMap usrMapObj : messageObj.getNotificationUserMap()) {
				userIdToThisMail.add(usrMapObj.getSendTo());
				userIdList.add(usrMapObj.getSendTo());
			}
			
			userIdToThisMail.add(logedInUser.getId());
			userIdList.add(logedInUser.getId());			
		}
				
		Notification savedNotification =  notificationRepo.save(notification);
		notificationDetailService.updateNotificationDetail(userIdToThisMail,savedNotification.getNotificationId());
		
		boolean isNewMessageStatusUpdated = userService.updateNewMailTrue(userIdList);
		
		return savedNotification;
	}

	// Search Page
	@Override
	public Page<Notification> getAllNotification(SearchCredentialDTO searchCredentialDTO) {
		
		try {			

			if (userService.haveNewMail() || searchCredentialDTO.getIsManualTrriger()) {								
				User logedInUser = authendicationFacade.getCurrentUserDetails();
				userService.updateNewMailFalse(logedInUser.getId());
				System.out.println("Page "+searchCredentialDTO.getPage());
				Pageable pageable = null;															
				pageable = PageRequest.of(searchCredentialDTO.getPage(), 10); //Always 10 item per page
				if (StringUtils.isEmpty(searchCredentialDTO.getSearchKeyWord())) {
					searchCredentialDTO.setSearchKeyWord("");
				}												
				
						               
			       
				
				
				
				
				// Increment Date By 1 
				// IT Convert Sat Jul 24 00:00:00 IST 2021 1 To Sat Jul 25 00:00:00 IST 2021 1 (Include to date record also)
				try {
					//Optional<Date> optionalDate  = Optional.of(searchCredentialDTO.getToDate());
					if(null != searchCredentialDTO.getToDate())
					{
						searchCredentialDTO.setToDate(commanService.incrementDate(searchCredentialDTO.getToDate(), 1));
					}
				}
				catch (Exception e) {
					System.out.println(e);
				}

				System.out.println(searchCredentialDTO.getFromDate());
				System.out.println(searchCredentialDTO.getToDate());
				
				Page<Notification> notificationSearchResult = null;
				
				if(searchCredentialDTO.getModuleName().equalsIgnoreCase(MODULE_NAME_ALL))
				 notificationSearchResult = notificationRepo.getAllNotifications(logedInUser.getId(),
						searchCredentialDTO.getSendFrom(), searchCredentialDTO.getSendTo(),
						searchCredentialDTO.getSubject(),
						searchCredentialDTO.getFromDate(), searchCredentialDTO.getToDate(),						
						searchCredentialDTO.getSearchKeyWord(), pageable);
				
				if(searchCredentialDTO.getModuleName().equalsIgnoreCase(MODULE_NAME_INBOX))
					 notificationSearchResult = notificationRepo.getInboxNotifications(logedInUser.getId(),
							searchCredentialDTO.getSendFrom(), searchCredentialDTO.getSendTo(),
							searchCredentialDTO.getSubject(),
							searchCredentialDTO.getFromDate(), searchCredentialDTO.getToDate(),						
							searchCredentialDTO.getSearchKeyWord(), pageable);
				
				if(searchCredentialDTO.getModuleName().equalsIgnoreCase(MODULE_NAME_SEND))
					 notificationSearchResult = notificationRepo.getSendNotifications(logedInUser.getId(),
							searchCredentialDTO.getSendFrom(), searchCredentialDTO.getSendTo(),
							searchCredentialDTO.getSubject(),
							searchCredentialDTO.getFromDate(), searchCredentialDTO.getToDate(),						
							searchCredentialDTO.getSearchKeyWord(), pageable);
				
				if(searchCredentialDTO.getModuleName().equalsIgnoreCase(MODULE_NAME_STARED))
					 notificationSearchResult = notificationRepo.getStaredNotifications(logedInUser.getId(),
							searchCredentialDTO.getSendFrom(), searchCredentialDTO.getSendTo(),
							searchCredentialDTO.getSubject(),
							searchCredentialDTO.getFromDate(), searchCredentialDTO.getToDate(),						
							searchCredentialDTO.getSearchKeyWord(), pageable);
				
				if(searchCredentialDTO.getModuleName().equalsIgnoreCase(MODULE_NAME_IMPORTANT))
					 notificationSearchResult = notificationRepo.getImportantNotifications(logedInUser.getId(),
							searchCredentialDTO.getSendFrom(), searchCredentialDTO.getSendTo(),
							searchCredentialDTO.getSubject(),
							searchCredentialDTO.getFromDate(), searchCredentialDTO.getToDate(),						
							searchCredentialDTO.getSearchKeyWord(), pageable);
				
				for (Notification notification : notificationSearchResult.getContent()) {
					List<NotificationDetail> notificationDetail = notificationDetailService.getNotificationDetail(logedInUser.getId(),notification.getNotificationId());
					List<NotificationMessageMap> notificationMessage = notificationMessageMapService
							.findLastMeesageOfNotificationId(notification, logedInUser);
					notification.setNotificationDetail(notificationDetail);
					notification.setNotificationMessageMap(notificationMessage);
				}
								
				return notificationSearchResult;
			} else {
				// Need to retur Stats of all are update in response entity
				return null;
			}

		} catch (Exception ex) {
			System.out.println("Exception" + ex);
			return null;
		}
	}

	@Override
	public Notification findNotificationById(Long notificationId) {
		User logedInUser = authendicationFacade.getCurrentUserDetails();
		
		Notification notification = notificationRepo.findNotificationById(logedInUser.getId(),notificationId);		
		
		List<NotificationDetail> notificationDetail = notificationDetailService.getNotificationDetail(logedInUser.getId(),notification.getNotificationId());
		List<NotificationMessageMap> notificationMessage = notificationMessageMapService.findAllMeesageOfNotificationId(logedInUser.getId(),notificationId);
		notification.setNotificationDetail(notificationDetail);
		notification.setNotificationMessageMap(notificationMessage);		
		
		return notification ;
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
		notificationMessageMap.setParentNotificationMessageMapId(LONG_ZERO);		
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
			notificationUserMap.setMappingType(NOTIFICATION_TYPE_REPRINT);
			notificationUserMapList.add(notificationUserMap);
		}
		
		NotificationUserMap notificationUserMapObj = new NotificationUserMap();
		notificationUserMapObj.setSendTo(logedInUser.getId());
		notificationUserMapObj.setMappingType(NotificationConstant.NOTIFICATION_MAPPING_TYPE_FROM);
		notificationUserMapList.add(notificationUserMapObj);
		
		notificationMessageMap.setNotificationUserMap(notificationUserMapList);
		notification.setNotificationMessageMap(notificationMessageMapList);
		notificationRepo.save(notification);

		return notification;
	}

	@Override
	public boolean updateLastUpdateTimeDate(Long notificationId) {
		if (notificationId == null || notificationId == 0)
			return false;
		int count = notificationRepo.updateLastUpdateTimeDate(new Date(), notificationId);
		System.out.println("Time Update" + count);
		return count > 0;
	}


}
