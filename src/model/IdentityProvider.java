package model;

import java.io.File;
import java.io.IOException;
import java.util.function.Predicate;

import Collections.ListInterface;
import Collections.ReferenceBasedList;

public class IdentityProvider implements IIdentityProvider {
	private ListInterface<User> users;
	private ISerializer<User> serializer;
	
	public IdentityProvider(ISerializer<User> serializer) {
		this.serializer = serializer;
	}
	
	@Override
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

	@Override
	public void addUser(String username, String password) throws IdentityException {
		if(!users.exists(u -> u.getUsername().equals(username))){
			users.append(new User(username, password));
		} else {
			throw new IdentityException("Username in use");
		}
	}

	@Override
	public void remove(String username) throws IdentityException {
		users.removeSingle(u -> u.getUsername().equals(username));
	}

	@Override
	public User getUser(Predicate<User> predicate) throws IdentityException {
		return users.findSingle(predicate);
	}

	@Override
	public void saveUsers() throws IOException {
		serializer.serialize(users, new File("users.dat"));
	}

	@Override
	public void loadUsers() throws IOException, ClassNotFoundException {
		users = serializer.deserialize(new File("users.dat"));
		if(users == null) {
			users = new ReferenceBasedList<User>();
		}
	}

}
