package com.gopro.service;

import org.springframework.web.multipart.MultipartFile;

public interface ProfileImageService {

	String uploadImage(MultipartFile file);

	byte[] getProfileImage(String fileName);

}
