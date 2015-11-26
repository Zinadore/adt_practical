package model;

import java.io.IOException;
import java.util.function.Predicate;

import Collections.ListInterface;

/**
 * This class is responsible for maintaining a list of registered users. It can also authenticate them.
 * Finally it is responsible for storing them and retrieving them to/from permanent storage.
 * It should be noted that the class should be treated as a singleton, although it does not implement this pattern.
 * 
 * @author Ioannis Boutsikas
 *
 */
public interface IIdentityProvider {
	/**
	 * Authenticates the user using the provided username and password. If no users
	 * with the username exist, an IdentityException is thrown. </br>
	 * If the password is not valid, an IdentityException is thrown.
	 * @param username the username of the user.
	 * @param password the password of the user.
	 * @return the user if authentication is successful
	 * @throws IdentityException if the user is not authenticated, or not found.
	 */
	public User authenticate(String username, String password) throws IdentityException;
	
	/**
	 * And a new user to the list maintained by the provider. The username must be unique, if not an IdentityException is thrown.
	 * @param username the username of the new user.
	 * @param password the password of the new user.
	 * @throws IdentityException if the username is not unique.
	 */
	public void addUser(String username, String password) throws IdentityException;
	
	/**
	 * Removes the user with matching username from the list maintained. If no matching user is found
	 * an IdentityException is thrown.
	 * @param username the username of the user.
	 * @throws IdentityException if no matching user is found.
	 */
	public void remove(String username) throws IdentityException;
	
	/**
	 * Returns the first user found, matching the specified predicate. Throws an IdentityException
	 * if no user found.
	 * @param predicate the predicate used to search the list maintained.
	 * @return the user found.
	 * @throws IdentityException if no matching user is found.
	 */
	public User getUser(Predicate<User> predicate) throws IdentityException;
	
	/**
	 * Returns the whole list of users maintained.
	 * @return the list of all users.
	 */
	public ListInterface<User> getAllUsers();
	
	/**
	 * Saves the list to permanent storage.
	 * @throws IOException
	 */
	public void saveUsers() throws IOException;
	
	/**
	 * Loads the list from permanent storage.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void loadUsers() throws IOException, ClassNotFoundException;
}
