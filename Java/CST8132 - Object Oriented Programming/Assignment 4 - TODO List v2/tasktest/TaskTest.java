/* File: TaskTest.java
 * Author: Stanley Pieda
 * Date: 2015
 * Description: JUnit test for use in Assignment 04
 * Note:
 * Exception handling for tests taken after book Jeff Langr, Andy Hunt and Dave Thomas (2015):
 * Jeff LAngr, Andy Hunt, Dave Thomas. (2015). Pragmatic Unit Testing in Java 8 with JUnit.
 * The Pragmatic Bookshelf, Dallas, Texas and Raleigh, North Carolina.
 * Paper copy: pages 31 to 34.
 * Available via Safari Books online, Algonquin College Library as well:
 * See Chapter 3: Digging Deeper into JUnit Assertions: Three Schools for Expecting Exceptions.
 * Algonquin College Safari Books link:
 * http://proquest.safaribooksonline.com.eztest.ocls.ca/book/programming/java/9781680500769/chapter-3-digging-deeper-into-junit-assertions/f_0030_html?uicode=algonquin
 */
package tasktest;

import static org.junit.Assert.*;

import org.junit.Test;

import task.Task;
import task.ValidationException;

public class TaskTest {

	@Test
	public void testTaskNoArgumentsContructorTitle() {
		Task task = new Task();
		String expected = "No title";
		String actual = task.getTitle();
		String message = "Default constructor did not set title correctly";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskNoArgumentsContructorPriority() {
		Task task = new Task();
		String expected = "No priority";
		String actual = task.getPriority();
		String message = "Default constructor did not set priority correctly";
		assertEquals(message, expected, actual); 
	}

	@Test
	public void testTaskNoArgumentsContructorIsComplete() {
		Task task = new Task();
		boolean expected = false;
		boolean actual = task.getIsComplete();
		String message = "Default constructor did not set isComplete correctly";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskTwoParameterConstructorTitle() throws ValidationException{
		String expected = "test title";
		String validPriority = "low";
		Task task = new Task(expected, validPriority);
		String actual = task.getTitle();
		String message = "2 parameter constructor did not set title correctly";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskTwoParameterConstructorPriority() throws ValidationException{
		String expected = "low";
		String validTitle = "a";
		Task task = new Task(validTitle, expected);
		String actual = task.getPriority();
		String message = "2 parameter constructor did not set priority correctly";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskTwoParameterConstructorIsComplete() throws ValidationException{
		boolean expected = false;
		String validTitle = "a";
		String validPriority = "low";
		Task task = new Task(validTitle, validPriority);
		boolean actual = task.getIsComplete();
		String message = "2 parameter constructor did not keep isComplete with default false";
		assertEquals(message, expected, actual);
	}

	@Test // new for Assignment 4
	public void testTaskThreeParameterConstructorTitle() throws ValidationException{
		String expected = "test title";
		String validPriority = "low";
		boolean trueIsComplete = true;
		Task task = new Task(expected, validPriority, trueIsComplete);
		String actual = task.getTitle();
		String message = "3 parameter constructor did not set title correctly";
		assertEquals(message, expected, actual);
	}

	@Test // new for Assignment 4
	public void testTaskThreeParameterConstructorPriority() throws ValidationException{
		String expected = "low";
		String validTitle = "a";
		boolean trueIsComplete = true;
		Task task = new Task(validTitle, expected, trueIsComplete);
		String actual = task.getPriority();
		String message = "3 parameter constructor did not set priority correctly";
		assertEquals(message, expected, actual);
	}

	@Test // new for Assignment 4
	public void testTaskThreeParameterConstructorIsComplete() throws ValidationException{
		boolean expected = true;
		String validTitle = "a";
		String validPriority = "low";
		Task task = new Task(validTitle, validPriority, expected);
		boolean actual = task.getIsComplete();
		String message = "3 parameter constructor did not set isComplete to true";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskGetSetTitle() throws ValidationException{
		Task task = new Task();
		String expected = "test title";
		task.setTitle(expected);
		String actual = task.getTitle();
		String message = "get or set title did not work correctly";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskGetSetPriority() throws ValidationException{
		Task task = new Task();
		String expected = "low";
		task.setPriority(expected);
		String actual = task.getPriority();
		String message = "get or set priority did not work correctly";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskGetSetIsCompleted() {
		boolean expected = true;
		Task task = new Task();
		task.setIsComplete(expected);
		boolean actual = task.getIsComplete();
		String message = "get or set isComplete did not work correctly";
		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskToStringIsCompleteTrue() throws ValidationException{
		Task task = new Task();
		String title = "title";
		String priority = "low";
		String expected = String.format("%s (%s) is complete", title, priority);
		String message = "toString did not return correct string with isComplete true";

		task.setTitle(title);
		task.setPriority(priority);
		task.setIsComplete(true);
		String actual = task.toString();

		assertEquals(message, expected, actual);
	}

	@Test
	public void testTaskToStringIsCompleteFalse() throws ValidationException{
		Task task = new Task();
		String title = "title";
		String priority = "low";
		String expected = String.format("%s (%s) is not complete", title, priority);
		String message = "toString did not return correct string with isComplete false";

		task.setTitle(title);
		task.setPriority(priority);
		task.setIsComplete(false);
		String actual = task.toString();

		assertEquals(message, expected, actual);
	}
	
	@Test
	public void testTaskCreateTabRecord() throws ValidationException{
		String goodTitle = "a";
		String goodPriority = "low";
		boolean goodIsComplete = true;
		String message = "createTabRecord() did not return string in correct format";
		String expected = String.format
				("%s\t%s\t%s", goodTitle, goodPriority, Boolean.toString(goodIsComplete));
		Task task = new Task(goodTitle, goodPriority, goodIsComplete);
		
		String actual = task.createTabRecord();
		
		assertEquals(message, expected, actual);
	}
	
	/* these tests fail if the exception in the annotation is not thrown
	 * Jeff Langr, Andy Hunt and Dave Thomas (2015)
	 */
	@Test(expected=ValidationException.class)
	public void testTaskSetTitleThrowsValidationExceptionForNullString() 
			throws ValidationException{
		String badTitle = null;
		Task task = new Task();
		task.setTitle(badTitle);
	}

	@Test(expected=ValidationException.class)
	public void testTaskSetTitleThrowsValidationExceptionForEmptyString() 
			throws ValidationException{
		String badTitle = "";
		Task task = new Task();
		task.setTitle(badTitle);
	}

	@Test(expected=ValidationException.class)
	public void testTaskSetTitleThrowsValidationExceptionForWhiteSpace() 
			throws ValidationException{
		String badTitle = "          ";
		Task task = new Task();
		task.setTitle(badTitle);
	}

	@Test(expected=ValidationException.class)
	public void testTaskSetTitleThrowsValidationExceptionForStringExceeds25Characters() 
			throws ValidationException{
		String badTitle = "12345678901234567890123456";
		Task task = new Task();
		task.setTitle(badTitle);
	}

	@Test(expected=ValidationException.class)
	public void testTaskSetPriorityThrowsValidationExceptionForNullString() 
			throws ValidationException{
		String badPriority = null;
		Task task = new Task();
		task.setPriority(badPriority);
	}

	@Test(expected=ValidationException.class)
	public void testTaskSetPriorityThrowsValidationExceptionForEmptyString() 
			throws ValidationException{
		String badPriority = "";
		Task task = new Task();
		task.setPriority(badPriority);
	}

	@Test(expected=ValidationException.class)
	public void testTaskSetPriorityThrowsValidationExceptionForWhiteSpace() 
			throws ValidationException{
		String badPriority = "          ";
		Task task = new Task();
		task.setPriority(badPriority);
	}

	@Test(expected=ValidationException.class)
	public void testTaskSetPriorityThrowsValidationExceptionForNotUsingHighMediumLow() 
			throws ValidationException{
		String badPriority = "bad-data";
		Task task = new Task();
		task.setPriority(badPriority);
	}
	
	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskTwoParameterConstructorThrowsValidationExceptionForTitleNullString() 
			throws ValidationException{
		String badTitle = null;
		String goodPriority = "low";
		Task task = new Task(badTitle, goodPriority);
	}

	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskTwoParameterConstuctorThrowsValidationExceptionForTitleEmptyString() 
			throws ValidationException{
		String badTitle = "";
		String goodPriority = "low";
		Task task = new Task(badTitle, goodPriority);
	}

	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskTwoParameterConstuctorThrowsValidationExceptionForTitleWhiteSpace() 
			throws ValidationException{
		String badTitle = "          ";
		String goodPriority = "low";
		Task task = new Task(badTitle, goodPriority);
	}

	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskTwoParameterConstuctorThrowsValidationExceptionForTitleExceeds25Characters() 
			throws ValidationException{
		String badTitle = "12345678901234567890123456";
		String goodPriority = "low";
		Task task = new Task(badTitle, goodPriority);
	}

	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskTwoParameterConstuctorThrowsValidationExceptionForPriorityNullString() 
			throws ValidationException{
		String badPriority = null;
		String goodTitle = "a";
		Task task = new Task(goodTitle, badPriority);
	}

	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskTwoParameterConstuctorThrowsValidationExceptionForPriorityEmptyString() 
			throws ValidationException{
		String badPriority = "";
		String goodTitle = "a";
		Task task = new Task(goodTitle, badPriority);
	}

	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskTwoParameterConstuctorThrowsValidationExceptionForPriorityWhiteSpace() 
			throws ValidationException{
		String badPriority = "          ";
		String goodTitle = "a";
		Task task = new Task(goodTitle, badPriority);
	}

	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskTwoParameterConstuctorThrowsValidationExceptionForPriorityNotUsingHighMediumLow() 
			throws ValidationException{
		String badPriority = "bad-data";
		String goodTitle = "a";
		Task task = new Task(goodTitle, badPriority);
	}

	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskThreeParameterConstructorThrowsValidationExceptionForTitleNullString() 
			throws ValidationException{
		String badTitle = null;
		String goodPriority = "low";
		boolean goodIsCompleted = true;
		Task task = new Task(badTitle, goodPriority, goodIsCompleted);
	}

	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskThreeParameterConstuctorThrowsValidationExceptionForTitleEmptyString() 
			throws ValidationException{
		String badTitle = "";
		String goodPriority = "low";
		boolean goodIsCompleted = true;
		Task task = new Task(badTitle, goodPriority, goodIsCompleted);
	}

	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskThreeParameterConstuctorThrowsValidationExceptionForTitleWhiteSpace() 
			throws ValidationException{
		String badTitle = "          ";
		String goodPriority = "low";
		boolean goodIsCompleted = true;
		Task task = new Task(badTitle, goodPriority, goodIsCompleted);
	}

	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskThreeParameterConstuctorThrowsValidationExceptionForTitleExceeds25Characters() 
			throws ValidationException{
		String badTitle = "12345678901234567890123456";
		String goodPriority = "low";
		boolean goodIsCompleted = true;
		Task task = new Task(badTitle, goodPriority, goodIsCompleted);
	}

	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskThreeParameterConstuctorThrowsValidationExceptionForPriorityNullString() 
			throws ValidationException{
		String badPriority = null;
		String goodTitle = "a";
		boolean goodIsCompleted = true;
		Task task = new Task(goodTitle, badPriority, goodIsCompleted);
	}

	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskThreeParameterConstuctorThrowsValidationExceptionForPriorityEmptyString() 
			throws ValidationException{
		String badPriority = "";
		String goodTitle = "a";
		boolean goodIsCompleted = true;
		Task task = new Task(goodTitle, badPriority, goodIsCompleted);
	}

	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskThreeParameterConstuctorThrowsValidationExceptionForPriorityWhiteSpace() 
			throws ValidationException{
		String badPriority = "          ";
		String goodTitle = "a";
		boolean goodIsCompleted = true;
		Task task = new Task(goodTitle, badPriority, goodIsCompleted);
	}

	@Test(expected=ValidationException.class) // new for Assignment 4
	public void testTaskThreeParameterConstuctorThrowsValidationExceptionForPriorityNotUsingHighMediumLow() 
			throws ValidationException{
		String badPriority = "bad-data";
		String goodTitle = "a";
		boolean goodIsCompleted = true;
		Task task = new Task(goodTitle, badPriority, goodIsCompleted);
	}

}
