package mocks;

import java.io.IOException;
import java.util.Comparator;
import java.util.function.Predicate;

import Collections.ListInterface;
import Collections.ReferenceBasedList;
import model.IIdentityProvider;
import model.IdentityException;
import model.User;
import model.UserAlphabeticalComparator;

public class IdentityProviderMock implements IIdentityProvider {
	
	private ListInterface<User> users;
	private Comparator<User> comp;
	
	public IdentityProviderMock() {
		comp = new UserAlphabeticalComparator();
		users = new ReferenceBasedList<User>();
		users.add(new User("John", "12345"), comp);
		users.add(new User("Aaron", "12345"), comp);
		users.add(new User("James", "12345"), comp);
		users.add(new User("Zeon", "12345"), comp);
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
			users.add(new User(username, password), comp);
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
	
	public ListInterface<User> getAllUsers() {
		return this.users;
	}
	
	@Override
	public void saveUsers() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadUsers() throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

}
