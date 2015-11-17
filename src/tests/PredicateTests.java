package tests;

import Collections.ListInterface;
import Collections.ReferenceBasedList;
import mocks.Person;

public class PredicateTests {
	
	public static void main(String[] args) {
		ListInterface<Person> people = new ReferenceBasedList<Person>();
		people.add(1, new Person("Mitsos", 1));
		people.add(2, new Person("Kitsos", 7));
		people.add(3, new Person("Ritsos", 25));
		people.add(4, new Person("Maria", 301));
		people.add(5, new Person("John", 50));
		people.add(6, new Person("Jane", 5));
		
		
	//	Person test = people.findSingle(p -> p.getName().equals("tera"));
	//	System.out.println(test);
		people.removeSingle(p -> p.getName().equals("Maria"));
		for(int i = 1; i <= people.size(); i++){
			System.out.println(people.get(i));
		}
	//	System.out.println(people.exists(p -> p.getAge() == 7));
	}
}
