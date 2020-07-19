package Exceptions;


public class PersonTester {
	
	public static void main(String[] args){

		
		try{
			Person p = new Person(); // name set to "Unknown"
			// as you test each call, which triggers an excep
			// comment it out and re-run the program to test
			// next case.
			//p.setFullName(null); // null
			//p.setFullName(""); // empty String
			//p.setFullName(" "); // white space
			//p.setFullName("12345678901234567890tuna"); // len
			p.setFullName("fish, tuna"); // contains comma
		}
		catch(FullNameException ex){
			
			System.out.println(ex.getMessage());
		}
	}}
