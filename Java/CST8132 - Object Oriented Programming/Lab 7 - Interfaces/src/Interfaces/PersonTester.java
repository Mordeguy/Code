package Interfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonTester {

	public static void main(String[] args) {

		PersonFirstNameComparator fnc = new PersonFirstNameComparator();
		LastNameComparator lnc = new LastNameComparator();
		AgeComparator ac = new AgeComparator();

		List<Person> p = new ArrayList<Person>() ;

		p.add (new Person("aaa", "bbb", 1));
		p.add (new Person("xyz", "abs", 2));
		p.add (new Person("kyj", "lmn", 3));

		System.out.println("Before sorting: (firstName, lastName, age)");

		for (Person p1 : p)
			System.out.println(p1);

		Collections.sort(p, fnc);

		System.out.println("\nAfter sorting by first name: (firstName, lastName, age)");

		for (Person p2 : p)
			System.out.println(p2);
		
		
		
		System.out.println("\nAfter sorting by last name: (firstName, lastName, age)");
		
		Collections.sort(p, lnc);
		
		for (Person p3 : p)
			System.out.println(p3);
		
		
		
		System.out.println("\nAfter sorting by age: (firstName, lastName, age)");
		
		Collections.sort(p, ac);
		
		for (Person p4 : p)
			System.out.println(p4);
		
		
		
		
		
		
	}
}