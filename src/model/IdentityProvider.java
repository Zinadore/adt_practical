package model;

import java.io.File;
import java.io.IOException;
import java.util.function.Predicate;

import Collections.ListInterface;
import Collections.ReferenceBasedList;
/**
 * This class is responsible for maintaining a list of registered users. It can also authenticate them.
 * Finally it is responsible for storing them and retrieving them to/from permanent storage.
 * It should be noted that the class should be treated as a singleton, although it does not implement this pattern.
 * 
 * @author Ioannis Boutsikas
 *
 */
/**
 * @author Ioannis Boutsikas
 *
 */
public class IdentityProvider implements IIdentityProvider {
	private ListInterface<User> users;
	private ISerializer<User> serializer;
	
	/**
	 * Creates an IdentityProvider using the ISerializer provided.
	 * The constructor is also used to declare any dependencies.
	 * @param serializer
	 */
	public IdentityProvider(ISerializer<User> serializer) {
		this.serializer = serializer;
	}
	
	/* (non-Javadoc)
     *  @see model.IIdentityProvider#authenticate(String username, String password) throws IdentityException
     */
	public User authenticate(String username, String password) throws IdentityException {
		if(users.exists(u -> u.getUsername().equals(username))) {
			User user = users.findSingle(u -> u.getUsername().equals(username));
			if(user.checkPassword(password)) {
				return user;
			}
			throw new IdentityException("Password did not match.");
		}
		throw new IdentityException("No user matching the username");
	}

	
	/* (non-Javadoc)
	 * @see model.IIdentityProvider#addUser(java.lang.String, java.lang.String)
	 */
	public void addUser(String username, String password) throws IdentityException {
		if(!users.exists(u -> u.getUsername().equals(username))){
			users.append(new User(username, password));
		} else {
			throw new IdentityException("Username in use");
		}
	}

	
	/* (non-Javadoc)
	 * @see model.IIdentityProvider#remove(java.lang.String)
	 */
	public void remove(String username) throws IdentityException {
		users.removeSingle(u -> u.getUsername().equals(username));
	}

	/* (non-Javadoc)
	 * @see model.IIdentityProvider#getUser(java.util.function.Predicate)
	 */
	public User getUser(Predicate<User> predicate) throws IdentityException {
		return users.findSingle(predicate);
	}
	
	/* (non-Javadoc)
	 * @see model.IIdentityProvider#getAllUsers()
	 */
	public ListInterface<User> getAllUsers() {
		return this.users;
	}
	
	/* (non-Javadoc)
	 * @see model.IIdentityProvider#saveUsers()
	 */
	public void saveUsers() throws IOException {
		serializer.serialize(users, new File("users.dat"));
	}

	/* (non-Javadoc)
	 * @see model.IIdentityProvider#loadUsers()
	 */
	public void loadUsers() throws IOException, ClassNotFoundException {
		users = serializer.deserialize(new File("users.dat"));
		if(users == null) {
			users = new ReferenceBasedList<User>();
		}
	}

}
