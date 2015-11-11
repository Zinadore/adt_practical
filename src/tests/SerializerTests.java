package tests;

import java.io.File;
import java.io.IOException;

import Collections.ListInterface;
import Collections.ReferenceBasedList;
import model.ISerializer;
import model.Serializer;
import model.User;

public class SerializerTests {

	public static void main(String[] args) {
		ISerializer<User> serializer = new Serializer<User>();
		ListInterface<User> users = new ReferenceBasedList<User>();
		ListInterface<User> users2 = new ReferenceBasedList<User>();
		
		users.append(new User("User1", "123456"));
		users.append(new User("User2", "123456"));
		users.append(new User("User3", "123456"));
		users.append(new User("User4", "123456"));
		try {
			serializer.serialize(users, new File("test_data/users.dat"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Serialized the list.");
		System.out.println("Attempting to retrieve it");
		try {
			users2 = serializer.deserialize(new File("test_data/users.dat"));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Checking deserialized data");
		for (int i = 1; i < users.size(); i++) {
			if(users.get(i).equals(users2.get(i))) {
				System.out.println("Iteration #"+(i+1)+"is ok");
			} else {
				System.out.println("Error at iteration #"+(i+1));
			}
		}
		
	}

}
