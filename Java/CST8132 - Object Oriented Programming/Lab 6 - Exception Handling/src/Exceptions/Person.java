package Exceptions;

public class Person {

	
	private String fullName;
	
	

	// Constructor that sets the fullName to "unknown" --------------------------------------||
	public Person(){
		
		fullName = "unknown";
		
	}
	
	
	
	
	// Set and Get Methods for fullName -----------------------------------------------------||
	public void setFullName (String fullName)throws FullNameException{
		
		validateData(fullName);
		this.fullName = fullName;
	
		
	}
	public String getFullName(){
		
		return fullName;
	}
	
	
	
	private void validateData (String st) throws FullNameException {
		
		if (st == null)
			throw new FullNameException ("Name cannot be null!");

		else if(st.isEmpty())
			throw new FullNameException("Name cannot be empty!");

		else if (st.trim().length() ==0)
			throw new FullNameException("Name cannot be less than zero characters long!");

		else if (st.length() > 20)
			throw new FullNameException("Name cannot be longer than 20 characters!");

		else if (st.contains(","))
			throw new FullNameException("Name cannot contain a comma!");
		
		else if (st.indexOf(",")>= 0)
			throw new FullNameException("Name cannot contain a comma!");
		
	}
	
	
	
	
	
	
} // End of Person class
