package com.imageuplod.RESTIMAGE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.imageuplod.RESTIMAGE.entity.UserRegister;
import com.imageuplod.RESTIMAGE.exceptions.RegisterException;
import com.imageuplod.RESTIMAGE.repository.UserRegisterRepository;
import com.imageuplod.RESTIMAGE.request.UpdateUserRequest;
import com.imageuplod.RESTIMAGE.utils.PropertiesApplication;
import com.imageuplod.RESTIMAGE.utils.UtilFunctions;
import org.springframework.security.core.userdetails.User;
import java.util.*;

@Service
public class RegisterService implements UserDetailsService{

	@Autowired
	private UserRegisterRepository userRegisterRepository;
	
	@Autowired
	private UtilFunctions utilFunctions;
	
	@Autowired
	private PropertiesApplication propertiesApplication;
	
	public UserRegister SaveRegister(UserRegister userRegister)
	{
		if(userRegisterRepository.findByEmail(userRegister.getEmail())!=null)
		{
			throw new RegisterException("Account With Mail: "+userRegister.getEmail()+" Exists!!");
		}
		userRegister.setUserID(utilFunctions.generateUserId(propertiesApplication.getUseridlength()));
		UserRegister returnValue=userRegisterRepository.save(userRegister);
		if(returnValue==null)
		{
			throw new RegisterException("Failed to Register User Please Check Data");
		}
		return returnValue;
	}
	public UserRegister getUser(String  userID)
	{
		if(userRegisterRepository.findByUserID(userID)==null)
		{
			throw new RegisterException("invalid UserID: "+userID+"");
		}
//		userRegister.setUserID(utilFunctions.generateUserId(propertiesApplication.getUseridlength()));
		UserRegister returnValue=userRegisterRepository.findByUserID(userID);
		if(returnValue==null)
		{
			throw new RegisterException("Failed to get User Please Check Data");
		}
		return returnValue;
	}
	
	public UserRegister updateUser(String  userID,UpdateUserRequest updateUserRequest)
	{
		if(userRegisterRepository.findByUserID(userID)==null)
		{
			throw new RegisterException("invalid UserID: "+userID+"");
		}
//		userRegister.setUserID(utilFunctions.generateUserId(propertiesApplication.getUseridlength()));
		UserRegister returnValue=userRegisterRepository.findByUserID(userID);
		returnValue.setFirstName(updateUserRequest.getFirstName());
		returnValue.setLastName(updateUserRequest.getLastName());
		if(userRegisterRepository.save(returnValue)==null)
		{
			throw new RegisterException("Failed to Update User, Try again!!");
		}
		
		return returnValue;
	}
	public UserRegister getUserByMail(String  email)
	{
		UserRegister returnValue=userRegisterRepository.findByEmail(email);
		if(returnValue==null)
		{
			throw new RegisterException("invalid Email: "+email+"");
		}
////		userRegister.setUserID(utilFunctions.generateUserId(propertiesApplication.getUseridlength()));
//		UserRegister returnValue=userRegisterRepository.findByUserID(userID);
//		returnValue.setFirstName(updateUserRequest.getFirstName());
//		returnValue.setLastName(updateUserRequest.getLastName());
//		if(userRegisterRepository.save(returnValue)==null)
//		{
//			throw new RegisterException("Failed to Update User, Try again!!");
//		}
//		
		return returnValue;
	}
	
	
	  @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		  UserRegister userRegister=userRegisterRepository.findByEmail(email);
		  System.out.println("******"+email);
	        return new User(userRegister.getEmail(),userRegister.getPassword(),new ArrayList<>());
	    }
}
