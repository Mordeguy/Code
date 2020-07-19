// Name: Nathan Abbey      Section: 301     Lab Teacher: Mauricio Orozco-Trujillo

import java.util.Scanner;

public class Assign4 {

	public static void main(String[] args) {

		int numberOfLedgers;
	
		Scanner input = new Scanner (System.in);
		
		System.out.print ("Enter the amount of monthly invoices: ");
		numberOfLedgers = input.nextInt();
		Ledger ldgr = new Ledger(numberOfLedgers);
	
		
		ldgr.getInvoiceInfo();
		ldgr.calculateMonthBills();
		ldgr.printInvoiceInfo();
		

		
	}

}
