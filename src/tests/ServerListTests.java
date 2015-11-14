package tests;

import Collections.ArrayBasedList;
import Collections.ListInterface;
import Collections.ReferenceBasedList;
import model.User;

public class ServerListTests {
	public static void main(String[] args) {
		ListInterface<User> users = new ReferenceBasedList<User>();
		ListInterface<User> users2 = new ArrayBasedList<User>();
		
		users.append(new User("User1", "123456"));
		users.append(new User("User2", "123456"));
		users.append(new User("User3", "123456"));
		users.append(new User("User4", "123456"));
		
		users2.append(new User("User1", "123456"));
		users2.append(new User("User2", "123456"));
		users2.append(new User("User3", "123456"));
		users2.append(new User("User4", "123456"));
		
		System.out.println("Fetching from LinkedList");
		long startTime = System.nanoTime();
		User user1 = users.findSingle(u -> u.getUsername().equals("User4"));
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("Task time: "+duration+" ns");
		System.out.println();
		System.out.println("Fetching from ArrayList");
		startTime = System.nanoTime();
		user1 = users2.findSingle(u -> u.getUsername().equals("User4"));
		endTime = System.nanoTime();
		duration = (endTime - startTime);
		System.out.println("Task time: " + duration + "ns");
	}
	
}
