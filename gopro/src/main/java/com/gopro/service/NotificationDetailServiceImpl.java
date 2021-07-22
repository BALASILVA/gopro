package com.gopro.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gopro.AuthendicationFacade.AuthendicationFacade;
import com.gopro.bene.NotificationDetail;
import com.gopro.bene.User;
import com.gopro.repository.NotificationDetailRepo;

@Service
public class NotificationDetailServiceImpl implements NotificationDetailService {

	private NotificationDetailRepo notificationDetailRepo;
	private AuthendicationFacade authendicationFacade;
	
	@Autowired
	public NotificationDetailServiceImpl(NotificationDetailRepo notificationDetailRepo, AuthendicationFacade authendicationFacade) {
		this.notificationDetailRepo = notificationDetailRepo;
		this.authendicationFacade = authendicationFacade;
	}


	@Override
	public List<NotificationDetail> getNotificationDetail(Long id, Long  notificationId) {
		return notificationDetailRepo.getNotificationDetail(id,notificationId);		
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean updateNotificationDetail(Set<Long> userIdToThisMail, Long  notificationId) {
		for (Long userId : userIdToThisMail) {
			notificationDetailRepo.updateNotificationDetail(userId, notificationId);	
		}
		return true;
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean updateNotificationDetail(List<Long> userIdList, Long notificationId) {
		for (Long userId : userIdList) {
			notificationDetailRepo.updateNotificationDetail(userId, notificationId);	
		}
		return true;
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean toogleFavNotification(Long notificationId) {		
		User user = authendicationFacade.getCurrentUserDetails();				
		 notificationDetailRepo.toogleFavNotification(notificationId,user.getId());
		 return true;
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean toggleImportantNotification(Long notificationId) {
		User user = authendicationFacade.getCurrentUserDetails();				
		notificationDetailRepo.toggleImportantNotification(notificationId,user.getId());		 
		return true;
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean makeFavNotificationInBulk(List<Long> notificationIdList,boolean isFav) {
		 User user = authendicationFacade.getCurrentUserDetails();				
		 System.out.println(notificationIdList.toString());
		 notificationDetailRepo.makeFavNotificationInBulk(notificationIdList,user.getId(), isFav);
		 return true;
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean makeImportantNotificationInBulk(List<Long> notificationIdList, boolean isImp) {
		User user = authendicationFacade.getCurrentUserDetails();				
		notificationDetailRepo.makeImportantNotificationInBulk(notificationIdList,user.getId(), isImp);		 
		return true;
	}

}
