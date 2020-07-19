//Nathan Abbey, Section: 301, Date: October 30, 2015, Lab Teacher: Mauricio Orozco-Trujillo, Assignment #2    


public class Assignment2 {

	public static void main(String[] args) {


		Invoice inv = new Invoice();


		inv.setMinutesFromUser();
		inv.setDatesFromUser();
		inv.calculateInvoice();
		inv.calcDays();
		inv.displayInvoice();

	}
}
