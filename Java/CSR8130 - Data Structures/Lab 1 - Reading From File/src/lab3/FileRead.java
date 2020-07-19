package lab3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileRead {

	String fileName;
	String errorMessage;


	
	public FileRead(){
	}
	
	
	
	public FileRead(String temp){
		fileName = temp;
	}
	
	
	
	
	
	@SuppressWarnings("resource")
	public void displayFile(){

		String option;
		Scanner scn = null;
		
		try {

			scn = new Scanner(fileName);

			
			while (scn.hasNextLine()){
				System.out.println(scn.nextLine());
			}

		} finally {
			scn.close();
		}

	}
}