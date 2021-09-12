package com.gopro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.OK;
import com.gopro.service.ProfileImageService;

@RestController
@RequestMapping(value = "/profileimage")
public class ProfileImageCntrl {
		
	ProfileImageService profileImageService;

	@Autowired
	public ProfileImageCntrl(ProfileImageService profileImageService) {		
		this.profileImageService = profileImageService;
	}	
	
	@PostMapping(value = "/upload")
	public String uploadImage(@RequestParam("file") MultipartFile file)
	{
		System.out.println("1"+file);
		String url = profileImageService.uploadImage(file);
		System.out.println(url);
		return url;
	}
	
	@GetMapping("/{fileName}")
	public ResponseEntity<byte[]> viewImage(@PathVariable String fileName)
	{
		byte[] image = profileImageService.getProfileImage(fileName);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}

}
