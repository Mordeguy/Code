// Name: Nathan Abbey      Section: 301     Lab Teacher: Mauricio Orozco-Trujillo

public class Ledger {


	private Invoice[] invoices;
	private int numInvoices;
	double total = 0;



	//Default Constructor
	public  Ledger() {
	}


	//Overloaded Constructor
	public Ledger(int i){
		numInvoices = i;
		invoices = new Invoice [numInvoices];

	}


	public void getInvoiceInfo(){
		for (int i = 0; i < numInvoices; i++){
			invoices [i] = new Invoice();
			System.out.println ("\nEnter info for invoice number " + i + " :");
			invoices[i].setCompanyNameFromUser();
			invoices[i].setBillAmountFromUser();
			invoices[i].setDateFromUser();
		}
	}

	public void printInvoiceInfo(){
		System.out.println("\nBill Summary: \n");
		for (int i =0; i < numInvoices; i++){
			System.out.println(invoices[i].toString());
		}
	}



	public double calculateMonthBills(){
		for (int i =0; i < numInvoices; i++){

			total += invoices[i].getAmount();

		}
		System.out.println ("\nTotal monthly bills: " + total);
		return total;

	}










}
