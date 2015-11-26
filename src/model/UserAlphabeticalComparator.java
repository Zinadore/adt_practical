package model;

import java.util.Comparator;

/**
 * A comparator used to impose an alphabetical ordering to a Collection of Users.
 * The ordering is based on the username of the two Users and ignores any case.
 * It should be noted that this perticular comparator is <i>consistent with equals</i> and compareTo.
 * @author Ioannis Boutsikas
 *
 */
public class UserAlphabeticalComparator implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		return o1.getUsername().compareToIgnoreCase(o2.getUsername());
	}

}
