import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.Scanner;



public class Reverser {

	public static void main(String[] args) {



		File f = new File ("C:\\Users\\Nay-thwan\\Desktop\\Algonquin College\\LEVEL II\\CST8132 - Object Oriented Programming\\CST8132 - O.O. Programming - Labs\\CST8132 - O.O. Programming - Lab 8\\wordlist.txt");
		Scanner scan = null;
		ArrayList<String> str = new ArrayList<String>();
		File f2 = new File ("C:\\Users\\Nay-thwan\\Desktop\\Algonquin College\\LEVEL II\\CST8132 - Object Oriented Programming\\CST8132 - O.O. Programming - Labs\\CST8132 - O.O. Programming - Lab 8\\reverser.txt");
		Formatter fmtr = null;



		System.out.println("Reading in data");

		try {
			scan = new Scanner (f);
		} catch (FileNotFoundException e) {

			System.out.print("This file path does not lead to a valid file");
		}




		while (scan.hasNext()){

			str.add(scan.nextLine());

		}




		System.out.println("Loaded " + str.size() + " words from file");
		System.out.println("Reversing word list");
		Collections.reverse(str);


		System.out.println("Writing word list");

		if (f2.exists()){

			System.out.println("File already exists");
			System.exit(0);
		}


		try {

			fmtr = new Formatter(f2);

		} catch (FileNotFoundException e){

			System.out.println("ERROR");
		}



		for (int i = 0; i <str.size(); i++){

			fmtr.format("%s%n", str.get(i));
		}



		fmtr.flush();
		fmtr.close();
		scan.close();


	}
}