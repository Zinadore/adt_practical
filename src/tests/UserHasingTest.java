package tests;

import model.User;

public class UserHasingTest {

	public static void main(String[] args) {
		User user = new User("Giorgio", "12345");
		if(user.checkPassword("12345")) {
			System.out.println("IT'S ALIVE BITCH!!!!!");
		}
	}

}
