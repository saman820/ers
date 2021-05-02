package com.example.hashing;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

public class Salt {
	public  final SecureRandom RAND = new SecureRandom();
	public  Optional<String> generateSalt (final int LENGTH){
		if(LENGTH<1) {
			System.err.println("error in generateSalt: length must be > 0");
			return Optional.empty();
		}
		
		byte[] salt = new byte[LENGTH];
		RAND.nextBytes(salt);
		
		return Optional.of(Base64.getEncoder().encodeToString(salt));
	}
}
