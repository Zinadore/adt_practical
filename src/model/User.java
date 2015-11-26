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
 * This class represents a user in the system. It is also responsible for
 * securing the password and verifying it. The current implementation hashes the
 * password using the SHA-256 algorithm. 
 * @author Boutsikas Ioannis, Charalampidis Giorgio
 */
public class User implements Serializable, Comparable<User>{

	private static final long serialVersionUID = 6146694074054550033L;
	/**
	 * The username of the user. It is the unique identifier 
	 * between objects as well.
	 */
	private String username;
	
	/**
	 * The password of the user. It is stored in a hashed and salted format
	 * so the retrieval of the original value is impossible.
	 */
    private String password;
    
    /**
     * The salt the password is hashed with. It is used to verify the validity
     * of the provided password during authentication.
     */
    private String salt;
    
    /**
     * Creates a user with the specified username and password.
     * The password will be hashed before storage.
     * @param username the username of the user.
     * @param password the password to be hashed.
     */
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
    
    /**
     * Returns the username of the user in string format.
     * @return a String representing the username.
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Checks the provided password against the user's password. The 
     * provided string is first salted then hashed.
     * @param password the provided password.
     * @return true if the passwords match, false otherwise.
     */
    public boolean checkPassword(String password) {
    	try 
    	{
			String tempHashedPassword = hash(salt, password);
			return tempHashedPassword.equals(this.password);
		} catch (NoSuchAlgorithmException e) {
			return false;
		}	
    }
    
    /**
     * Compares one object of type User to another User.
     * It is based on the comparison between the two usernames.
     * <p>Returns <b>-1</b> if the username of the other user is lexicogramatical <b>greater</b> than this user's username.</p>
     * <p>Returns <b>0</b> if the username of the other user is lexicogramatical <b>equal</b> to this user's username.</p>
     * <p>Returns <b>1</b> if the username of the other user is lexicogramatical <b>less</b> than this user's username.</p>
     * @return a negative integer, zero, or a positive integer as this object is less than, 
     * equal to, or greater than the specified object.
     */
	@Override
	public int compareTo(User other) {
		return this.username.compareToIgnoreCase(other.getUsername());
	}
	
	/**
	 * Provides a string representation of the state of the object.
	 */
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
	/**
	 * Hashes the password after salting it.
	 * @param salt the salt to salt the password.
	 * @param password the password to be hashed.
	 * @return a String containing the now hashed password.
	 * @throws NoSuchAlgorithmException
	 */
	private String hash(String salt, String password) throws NoSuchAlgorithmException
	{
		password = password + salt;
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(password.getBytes());
		String encryptedString = new String(messageDigest.digest());
		return encryptedString;
	}
	
	/**
	 * Creates a numeric salt to use in hashing.
	 * @return the hash in String format.
	 */
	private String createSalt()
	{
		Random rand = new Random();
		int value = rand.nextInt(899)+100;
		return Integer.toString(value);
	}

	
}


