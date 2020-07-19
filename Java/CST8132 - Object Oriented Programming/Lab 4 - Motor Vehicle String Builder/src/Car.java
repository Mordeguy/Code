
public class Car extends MotorVehicle {

	int trunkCapacity;
	
	public Car (String manufacturer, int topSpeed, int trunkCapacity){
		
		super(manufacturer, topSpeed);
		this.trunkCapacity = trunkCapacity;
		System.out.println("Returning from overloaded Car constructor");
	}
	
	
	
	public Car(){
		
		this("UnknownCar", 100, 1);
		System.out.println("Returning from default Car constructor");
	}
	
	 
	@Override
	public String toString(){
		
		StringBuilder sb = new StringBuilder(super.toString());
		
		sb.append(" trunkCapacity: " + trunkCapacity);
		
		return sb + "";
	}
	
}
