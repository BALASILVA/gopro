package com.gopro.service;

import org.springframework.data.domain.Page;

import com.gopro.bene.Invoice;
import com.gopro.bene.Notification;
import com.gopro.bene.SearchCredentialDTO;

public interface NotificationService {

	public Notification findNotificationByInvoiceId(Long invoiceId);

	public Notification sendReprintNotification(Invoice invoice);

	public Page<Notification> getAllNotification(SearchCredentialDTO searchCredentialDTO);

	public boolean getUpdateAllNotification(SearchCredentialDTO searchCredentialDTO);

	public Notification addNewMessageNotification(Notification notification);

	public boolean updateLastUpdateTimeDate(Long notificationId);
}
