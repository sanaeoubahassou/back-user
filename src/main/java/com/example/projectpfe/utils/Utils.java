package com.example.projectpfe.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Utils {
	@Bean
	public static String generateIgg() {
		Random random = new Random();
	    String prefix = random.nextBoolean() ? "L" : "J"; 
	    int suffix = random.nextInt(10000000); 

	    return prefix + String.format("%07d", suffix);
	}
	
	@Bean
	public static String generatePassword() {
	    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuwxyz-@_0123456789";
	    int length = 12;
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < length; i++) {
	        int index = random.nextInt(characters.length());
	        sb.append(characters.charAt(index));
	    }
	    return sb.toString();
	}
}
