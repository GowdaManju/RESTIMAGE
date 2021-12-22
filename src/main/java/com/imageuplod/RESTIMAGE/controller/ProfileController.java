package com.imageuplod.RESTIMAGE.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.imageuplod.RESTIMAGE.entity.ProfileImages;
import com.imageuplod.RESTIMAGE.entity.UserRegister;
import com.imageuplod.RESTIMAGE.exceptions.RegisterException;
import com.imageuplod.RESTIMAGE.service.ProfileService;

@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "http://localhost:420")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;

//	@PostMapping(value="/{userId}")
//	public ProfileImages saveProfile(@PathVariable("userId") String userID,@RequestParam("profile") MultipartFile file) throws IOException
//	{
//		System.out.println("Original Image Byte Size - " + file.getBytes().length);
//		
//		
//		return null;
//	}
	@PostMapping("/{userId}")
	public ProfileImages saveProfile(@PathVariable("userId") String userId,@RequestParam("profile") MultipartFile profile) throws IOException
	{
		System.out.println(profile.getSize());
		
		ProfileImages profileImages=profileService.saveProfile(userId, profile.getBytes());
		
		return profileImages;
		
//		return ResponseEntity<ProfileImages>(profileImages,HttpStatus.CREATED);
	}
	
        @GetMapping("/about")
	public String getAbout()
	{
		return "This is RestImage Project dgvdvdvd";
		 
	}

	@GetMapping("/{userId}")
	public ProfileImages getProfileImages(@PathVariable("userId") String userId)
	{
		ProfileImages returnValue=profileService.getProfileById(userId);
		return returnValue;
		
	}
	@DeleteMapping("mg/{userId}")
	public ResponseEntity<Object> removeProfileImage(@PathVariable("userId") String userId)
	{
		profileService.deleteProfileById(userId);
	
		return new ResponseEntity<Object>("Delete Success",HttpStatus.ACCEPTED);
	}
	@PutMapping("/{userId}")
	public ResponseEntity<ProfileImages> updateProfile(@PathVariable("userId") String userId,@RequestParam("profile") MultipartFile profile) throws IOException
	{
		System.out.println(profile.getSize());
		
		if(profileService.updateProfile(userId, profile.getBytes())==false)
		{
			throw new RegisterException("Failed to Update profile, Try Again!!!");
		}
//		
//		return profileImages;
		return new ResponseEntity<ProfileImages>(profileService.getProfileById(userId),HttpStatus.OK);
//		return null;
	}
}
