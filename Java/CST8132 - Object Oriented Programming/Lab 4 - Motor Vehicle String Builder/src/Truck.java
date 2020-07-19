
public class Truck extends MotorVehicle{
	
	int towingCapacity;
	
	public Truck(String manufacturer, int topSpeed, int towingCapacity){
		
		super(manufacturer, topSpeed);
		this.towingCapacity = towingCapacity;
		
		System.out.println("Returning from overloaded Truck constructor");
	}
	
	public Truck(){
		
		this ("UnknownTruck", 95, 11000);
		
		System.out.println("Returning from default Truck constructor");
	}
	
	@Override
	public String toString(){
		
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append(" towingCapacity: " + towingCapacity);
		return sb + "";
	}

}
