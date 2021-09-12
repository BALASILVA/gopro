package com.gopro.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gopro.AuthendicationFacade.AuthendicationFacade;
import com.gopro.bene.User;

@Service
public class ProfileImageServiceImpl implements ProfileImageService {

	private AuthendicationFacade authendicationFacade;
	private UserService userService;

	@Value("${user.profileimage.path}")
	String FILE_PATH;

	@Autowired
	public ProfileImageServiceImpl(UserService userService, AuthendicationFacade authendicationFacade) {
		this.userService = userService;
		this.authendicationFacade = authendicationFacade;
	}

	@Override
	public String uploadImage(MultipartFile profileImage) {
				
		User logInUser = authendicationFacade.getCurrentUserDetails();
		Long time = new Date().getTime();
		String imageName = time + profileImage.getOriginalFilename();
		String profileImageUrl = "/profileimage/"+imageName;
		
		File directory = new File(FILE_PATH);

		if (!directory.exists())
			directory.mkdirs();

		try {
			byte[] bytes = profileImage.getBytes();
			Path path = Paths.get(FILE_PATH + imageName);
			Files.write(path, bytes);
			boolean isUrlUpdated = userService.updateProfileImageUrl(logInUser.getId(), profileImageUrl);
			
			System.out.println(isUrlUpdated+" "+profileImageUrl);
			
			if (isUrlUpdated)
				return profileImageUrl;
			
		} catch (IOException e) {
			System.out.println(e);
			return null;
		}
		return null;
	}

	@Override
	public byte[] getProfileImage(String imageName) {
		
		byte[] image = null;
		try {
			Path path = Paths.get(FILE_PATH + imageName);
			image = Files.readAllBytes(path);
		} catch (Exception e) {
		}
		return image;
	}

}
