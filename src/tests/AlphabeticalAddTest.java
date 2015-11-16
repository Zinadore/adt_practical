package tests;

import Collections.ArrayBasedList;
import Collections.ListInterface;
import Collections.ReferenceBasedList;
import model.User;
import model.UserAlphabeticalComparator;

public class AlphabeticalAddTest {

	public static void main(String[] args) {
		UserAlphabeticalComparator comp = new UserAlphabeticalComparator();
		ListInterface<User> list = new ReferenceBasedList<User>();
		ListInterface<User> list2 = new ArrayBasedList<User>();
		
		list.add(new User("Zebra", "123456"), comp);
		list.add(new User("Adam", "123456"), comp);
		list.add(new User("Aaron", "123456"), comp);
		list.add(new User("George", "123456"), comp);
		list.add(new User("Urue", "123456"), comp);
		
		for(int i = 1; i <= list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		list2.add(new User("Zebra", "123456"), comp);
		list2.add(new User("Adam", "123456"), comp);
		list2.add(new User("Aaron", "123456"), comp);
		list2.add(new User("George", "123456"), comp);
		list2.add(new User("Urue", "123456"), comp);
		
		for(int i = 1; i <= list2.size(); i++) {
			System.out.println(list2.get(i));
		}
		//System.out.println(comp.compare(new User("George", "123456"), new User("Adam", "123456")));
		
	}

}
