package com.imageuplod.RESTIMAGE.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class ProfileImages {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique = true)
	private String userID;
	
	@Lob
	@Column(nullable = true)
	private byte[] profile;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public byte[] getProfile() {
		return profile;
	}

	public void setProfile(byte[] profile) {
		this.profile = profile;
	}
}
