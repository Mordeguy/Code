package Exceptions;


public class FullNameException extends Exception{

	
	public FullNameException(){
		super("Full name not correct format.");
	}
	
	
	public FullNameException(String message){
		super(message);
	}
	
	
	public FullNameException(String message, Throwable throwable){
		super(message, throwable);
	}
	
	
	public FullNameException(Throwable throwable){
		super(throwable);
	}
}