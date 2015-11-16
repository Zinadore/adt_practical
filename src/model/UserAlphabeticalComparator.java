package model;

import java.util.Comparator;

public class UserAlphabeticalComparator implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		return o1.getUsername().compareToIgnoreCase(o2.getUsername());
	}

}
