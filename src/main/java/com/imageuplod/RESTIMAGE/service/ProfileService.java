package com.imageuplod.RESTIMAGE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imageuplod.RESTIMAGE.entity.ProfileImages;
import com.imageuplod.RESTIMAGE.entity.UserRegister;
import com.imageuplod.RESTIMAGE.exceptions.RegisterException;
import com.imageuplod.RESTIMAGE.repository.ProfilesRepository;
import com.imageuplod.RESTIMAGE.repository.UserRegisterRepository;

@Service
public class ProfileService {
	
	@Autowired
	private ProfilesRepository profilesRepository;
	
	@Autowired
	private UserRegisterRepository userRegisterRepository;
	
	public ProfileImages saveProfile(String userId,byte[] image)
	{
		if(userRegisterRepository.findByUserID(userId)==null)
		{
			throw new RegisterException("Invalid UserId!!");
		}
		
		ProfileImages images=new ProfileImages();
		images.setUserID(userId);
		images.setProfile(image);
		
		ProfileImages savedProfile=profilesRepository.saveAndFlush(images);
		if(savedProfile==null)
		{
			throw new RegisterException("Failed to Set Profile Image, Try Again!!");
		}
		
		return savedProfile;
	}
	
	public ProfileImages getProfileById(String userId)
	{
		if(userRegisterRepository.findByUserID(userId)==null)
		{
			throw new RegisterException("Invalid UserId!!");
		}
		
		
		ProfileImages savedProfile=profilesRepository.findByUserID(userId);
		if(savedProfile==null)
		{
			throw new RegisterException("Failed to Set Profile Image, Try Again!!");
		}
		
		return savedProfile;
	}
	public boolean deleteProfileById(String userId)
	{
		if(userRegisterRepository.findByUserID(userId)==null)
		{
			throw new RegisterException("Invalid UserId!!");
		}

		profilesRepository.removeProfile(userId);
		if(profilesRepository.findByUserID(userId).getProfile()!=null)
		{
			throw new RegisterException("Failed to remove Profile Image, Try Again!!");
		}
		
		return true;
	}
	
	public boolean updateProfile(String userId,byte[] image)
	{
		if(userRegisterRepository.findByUserID(userId)==null)
		{
			throw new RegisterException("Invalid UserId!!");
		}
		
//		ProfileImages images=new ProfileImages();
//		images.setUserID(userId);
//		images.setProfile(image);
		
		int count=profilesRepository.updateProfile(image,userId);
		if(count<=0)
		{
			throw new RegisterException("Failed to update Profile Image, Try Again!!");
		}
		
		return true;
	}

}
