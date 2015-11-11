package tests;

import java.io.IOException;

import mocks.SerializerMock;
import model.IIdentityProvider;
import model.ISerializer;
import model.IdentityException;
import model.IdentityProvider;
import model.User;

public class IdentityProviderTests {

	public static void main(String[] args) {
		ISerializer<User> serializer = new SerializerMock();
		IIdentityProvider idp = new IdentityProvider(serializer);
		
		//Load users from mock
		try {
			System.out.println("Loading users");
			idp.loadUsers();
			System.out.println("Load successful");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Authenticating user1");
			User user = idp.authenticate("User1", "123456");
			System.out.println("Authentication completed");

			System.out.println("Authenticating user2");
			User user2 = idp.authenticate("User2", "test");
			System.err.println("Authentication completed");
		} catch (IdentityException e) {
			System.out.println("User2 failed as expected");
		}
		
		try {
			idp.addUser("User10", "test");
			System.out.println("Added User10");
		} catch (IdentityException e) {
			System.out.println("Failed to add User10");
		}
		
		try {
			idp.remove("User2");
			System.out.println("Removed User2");
		} catch (IdentityException e) {
			System.out.println("Failed to remove User2");
		}
		
		try {
			User user = idp.getUser(u -> u.getUsername().equals("User3"));
			System.out.println("Retrieved user3");
		} catch (IdentityException e) {
			System.out.println("Did not retrieve user3");
		}
	}

}
