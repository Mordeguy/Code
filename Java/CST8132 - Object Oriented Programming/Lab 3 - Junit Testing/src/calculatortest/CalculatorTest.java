package calculatortest;

import static org.junit.Assert.*;

import org.junit.Test;

import calculator.Calculator;

public class CalculatorTest{

	final double EPSILON = 1e-7;
	
	
	@Test
	public void testAdd() {
		Calculator calc = new Calculator(2.0);
		calc.add(2.0);
		assertEquals (4.0, calc.getAccumulator(), EPSILON);
	}
	
	
	@Test
	public void testDivideByZero() {
		Calculator calc = new Calculator(4.0);
		calc.divide(0);
		assertEquals (Double.POSITIVE_INFINITY, calc.getAccumulator(), EPSILON);
	}
	
	
	
	@Test
	public void testDivideBySelf() {
		Calculator calc = new Calculator(2.0);
		calc.divide(2.0);
		assertEquals (1.0, calc.getAccumulator(), EPSILON);
	}
	
	
	@Test
	public void testMultiply() {
		Calculator calc = new Calculator(4.0);
		calc.multiply(2.0);
		assertEquals (8.0, calc.getAccumulator(), EPSILON);
	}
	
	
	@Test
	public void testMultiplyForInfinity() {
		Calculator calc = new Calculator(Double.MAX_VALUE);
		calc.multiply(2.0);
		assertEquals (Double.POSITIVE_INFINITY, calc.getAccumulator(), EPSILON);
	}
	
	
	@Test
	public void testSqrtPositive() {
		Calculator calc = new Calculator(4.0);
		calc.sqrt();
		assertEquals (2.0, calc.getAccumulator(), EPSILON);
	}
	
	@Test
	public void testSqrtNegative() {
		Calculator calc = new Calculator(-6);
		calc.sqrt();;
		assertEquals (Double.NaN, calc.getAccumulator(), EPSILON);
	}
	

	

	

	

	

}
