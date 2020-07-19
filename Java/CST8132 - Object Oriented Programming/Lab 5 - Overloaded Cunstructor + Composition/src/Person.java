
public class Person {

	private Name name;
	private int age;
	
	
	
	public Person(String first, String middle, String last, int age){
		
		name = new Name(first, middle, last);	
		this.age = age;
		
	}
	
	
	public Person (Name name, int age){
		this.name = name;
		this.age = age;
	}


	
	public String details(){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(name.getFullName()).append("age: " + age);
		
		return sb.toString();
	}
	
	
}
