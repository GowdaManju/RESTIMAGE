package com.imageuplod.RESTIMAGE.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.sun.tools.doclint.Env;

@Component
public class UtilFunctions {
	
	
	private String ALPHABET="ABCDEFGHIJKLMNOPQURSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	private final Random RANDOM=new SecureRandom();
	
	
	public String generateUserId(int size)
	{
		StringBuilder useridBuilder=new StringBuilder();
		for(int i=0;i<size;i++)
		{
			useridBuilder.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return useridBuilder.toString();
	}
}


