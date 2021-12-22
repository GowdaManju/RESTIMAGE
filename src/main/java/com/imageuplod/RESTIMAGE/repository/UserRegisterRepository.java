package com.imageuplod.RESTIMAGE.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imageuplod.RESTIMAGE.entity.UserRegister;

public interface UserRegisterRepository extends JpaRepository<UserRegister, Long>{
	
	public UserRegister findByEmail(String email);
	public UserRegister findByUserID(String userId);
	
	
	

}
