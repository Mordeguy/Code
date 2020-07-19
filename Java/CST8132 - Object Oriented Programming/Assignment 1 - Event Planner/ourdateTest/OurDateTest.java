package ourdateTest;
/* File Name: 		OurDateTest.java
 * Course Name:		Object Oriented Programming
 * Lab Section:		301
 * Student Name:	Nathan M. Abbey
 * Date:			2016 / 02 / 11
 */
import static org.junit.Assert.*;
import assign1.OurDate;

import org.junit.Test;

/* This test class tests to ensure the default and overloaded constructor are working correctly */

public class OurDateTest {


	final double EPSILON = 1e-7;

	// Tests the year setting for the default constructor is working.
	@Test
	public void testDefaultConstructorForYear() {
		OurDate date = new OurDate ();
		assertEquals ("Year is initialized to 0",0, date.getYear(), EPSILON);
	}

	// Tests the month setting for the default constructor is working.
	@Test
	public void testDefaultConstructorForMonth() {
		OurDate date = new OurDate ();
		assertEquals ("Month is initialized to 1", 1, date.getMonth(), EPSILON);
	}

	// Tests the day setting for the default constructor is working.
	@Test
	public void testDefaultConstructorForDay() {
		OurDate date = new OurDate ();
		assertEquals ("Day is initialized to 0", 0, date.getDay(), EPSILON);
	}



	// Tests the year setting for the overloaded constructor is working.
	@Test
	public void testOverloadedConstructorForYear() {
		OurDate date = new OurDate (0, 0, 2001);
		assertEquals ("This overloaded constructor sets year to 2001", 2001, date.getYear(), EPSILON);
	}

	// Tests the year setting for the overloaded constructor is working.
	@Test
	public void testOverloadedConstructorForMonth() {
		OurDate date = new OurDate (0, 4, 0);
		assertEquals ("This overloaded constructor sets month to 4",4, date.getMonth(), EPSILON);
	}


	// Tests the year setting for the overloaded constructor is working.
	@Test
	public void testOverloadedConstructorForDay() {
		OurDate date = new OurDate (22, 0,0);
		assertEquals("This overloaded constructor sets day to 22", 22, date.getDay(), EPSILON);
	} 
	
} // End of OurDateTest Class