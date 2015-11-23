/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 *
 * @author Boutsikas Ioannis, Charalampidis Giorgio
 */
public class User implements Serializable, Comparable<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6146694074054550033L;
	private String username;
    private String password;
    private String salt;
    
    public User(String username, String password)
    {
    	try 
    	{
			this.username = username;
			salt = createSalt();
			this.password = hash(salt, password);
		} 
    	catch (NoSuchAlgorithmException e) 
    	{
		}
    }
    
    public String getUsername() {
        return username;
    }
    
    public boolean checkPassword(String password) {
    	try 
    	{
			String tempHashedPassword = hash(salt, password);
			return tempHashedPassword.equals(this.password);
		} catch (NoSuchAlgorithmException e) {
			return false;
		}	
    }

	@Override
	public int compareTo(User other) {
		return this.username.compareToIgnoreCase(other.getUsername());
	}
	
	@Override
	public String toString() {
		return "[User: username = "+ username +"]";
	}
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	private String hash(String salt, String password) throws NoSuchAlgorithmException
	{
		password = password + salt;
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(password.getBytes());
		String encryptedString = new String(messageDigest.digest());
		return encryptedString;
	}

	private String createSalt()
	{
		Random rand = new Random();
		int value = rand.nextInt(899)+100;
		return Integer.toString(value);
	}

	
}


