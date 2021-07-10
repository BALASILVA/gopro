package com.gopro.AuthendicationFacade;

import com.gopro.bene.User;

public interface AuthendicationFacade {
	User getCurrentUserDetails();

	String getCurrentUserName();
}
