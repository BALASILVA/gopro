package com.gopro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gopro.bene.NotificationUserMap;
import com.gopro.repository.UserNewMailMappingRepo;

@Service
public class UserNewMailMappingServiceImpl implements UserNewMailMappingService {

	private UserNewMailMappingRepo userNewMailMappingRepo;
	
	
	@Autowired
	public UserNewMailMappingServiceImpl(UserNewMailMappingRepo userNewMailMappingRepo) {
		this.userNewMailMappingRepo = userNewMailMappingRepo;
	}


}
