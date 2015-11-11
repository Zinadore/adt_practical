package model;

import java.io.IOException;
import java.util.function.Predicate;

public interface IIdentityProvider {
	public User authenticate(String username, String password) throws IdentityException;
	public void addUser(String username, String password) throws IdentityException;
	public void remove(String username) throws IdentityException;
	public User getUser(Predicate<User> predicate) throws IdentityException;
	public void saveUsers() throws IOException;
	public void loadUsers() throws IOException, ClassNotFoundException;
}
