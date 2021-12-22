package com.imageuplod.RESTIMAGE.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties("mg")
@Data
public class PropertiesApplication {
	
	private int useridlength;

	public int getUseridlength() {
		return useridlength;
	}

	public void setUseridlength(int useridlength) {
		this.useridlength = useridlength;
	}
	

}
