/* File Name: calcTaxTest.java
 * Course Name: Object Oriented Programming - CST8132 
 * Lab Section: 301
 * Student Name: Nathan M. Abbey
 * Date: March 7, 2016
 */
package EmployeeCalculateTaxTest;
import static org.junit.Assert.*;
import Assign2.Employee;
import Assign2.Executive;
import org.junit.Test;
/*
 * This is the J-Unit class for testing the taxes are being calculated correctly.'
 */
public class calcTaxTest {
	
	
	// Tests the first tax bracket
	@Test
	public void testCalculateTaxableIncomeBelowFirstLevelIncome() {
		final double EPSILON = 1e-7;
		Employee ex = new Executive();
		ex.setTaxableIncome(45000);
		assertEquals ("Your first tax bracket is calculating incorrectly. ", 15975.00, ex.calculateTax() , EPSILON);
	}

	
	// Tests the second tax bracket
	@Test
	public void testCalculateTaxableIncomeBetweenFirstAndSecondLevelIncome() {
		final double EPSILON = 1e-7;
		Employee ex = new Executive();
		ex.setTaxableIncome(80000);
		assertEquals("Your second tax bracket is calculating incorrectly. ",13909.49, ex.calculateTax() , EPSILON);
	}
	
	
	// Tests the third tax bracket
	@Test
	public void testCalculateTaxableIncomeBetweenSecondAndThirdLevelIncome() {
		final double EPSILON = 1e-7;
		Employee ex = new Executive();
		ex.setTaxableIncome(105000);
		assertEquals("Your third tax bracket is calculating incorrectly. ",24015.254999999997, ex.calculateTax(),  EPSILON);
	}
	
	
	
	// Tests the fourth tax bracket
	@Test
	public void testCalculateTaxableIncomeBetweenThirdAndFourthLevelIncome() {
		Employee ex = new Executive();
		final double EPSILON = 1e-7;
		ex.setTaxableIncome(200000);
		assertEquals("Your fourth tax bracket is calculating incorrectly. ", 46316.88499999995, ex.calculateTax(), EPSILON);
	}
	
	
	
	// Tests the final tax bracket
	@Test
	public void testCalculateTaxableIncomeAboveFourthLevelIncome() {
		Employee ex = new Executive();
		final double EPSILON = 1e-2;
		ex.setTaxableIncome(500000);
		assertEquals("Your final tax bracket is calculating incorrectly. ",145316.89, ex.calculateTax(), EPSILON);
	}
	
	
}
