
public class Name {

	//public static void main(String[] args) {
		// TODO Auto-generated method stub

	private String first;
	private String middle;
	private String last;
	

	
	public Name (String first, String middle, String last){

		this.first = first;
		this.middle = middle;
		this.last = last;
	}
	
	
	public Name (String first, String last){
		
		this.first = first;
		this.last = last;
		middle = "unknown";
	}
	
	
	public Name (String first){
		
		this.first = first;
		middle = "unknown";
		last = "unknown";
	}


	public Name(){

		first = "unknown";
		middle = "unknown";
		last = "unknown";
	}
	
	public String getFirst(){
		
		return first;
	}
	
	
	public String getMiddle(){
		
		return middle;
	}
	
	
	public String getLast(){
		
		return last;
	}
	
	
	
	public String getFullName(){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(first + " ").append(middle + " ").append(last + " ");
		
		return sb.toString();
	}
	

}
