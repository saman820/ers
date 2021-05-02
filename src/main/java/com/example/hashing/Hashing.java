package com.example.hashing;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashing {
	public  final int ITERATIONS = 65536;
	public  final int KEY_LENGTH = 50;
	public  final String ALGORITHM ="PBKDF2WithHmacSHA512";
	
	public  Optional<String> hashPassword (String password, String salt){
		char[] chars = password.toCharArray();
	    byte[] bytes = salt.getBytes();
	    
	    PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);
	    Arrays.fill(chars, Character.MIN_VALUE);
	    
	    
	    try {
	        SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
	        byte[] securePassword = fac.generateSecret(spec).getEncoded();
	        return Optional.of(Base64.getEncoder().encodeToString(securePassword));
	        

	      } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
	        System.err.println("Exception encountered in hashPassword()");
	        return Optional.empty();

	      } finally {
	        spec.clearPassword();
	      }
	}
	
	public boolean verifyPassword(String password, String key, String salt) {
		Optional<String> optEncrypted = hashPassword(password, salt);
        if (!optEncrypted.isPresent()) return false;
        return optEncrypted.get().equals(key);
	}
	
}
