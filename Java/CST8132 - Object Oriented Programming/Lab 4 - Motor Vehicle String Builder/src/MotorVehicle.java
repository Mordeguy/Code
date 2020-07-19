
public class MotorVehicle {
	
	
	
	String manufacturer;
	int topSpeed;
	
	
	
	
	public MotorVehicle(){
		this("UnknownMotorVehicle", 0);
		System.out.println("Returning from the default MotorVehicle constructor");
	}
	
	
	
	
	public MotorVehicle (String manufacturer, int topSpeed ){
		this.manufacturer = manufacturer;
		this.topSpeed = topSpeed;
		System.out.println("Returning from the overloaded MotorVehicle constructor");
	}
	
	
	@Override  //Says you want to overwrite the objects toString
	public String toString(){
		
		StringBuilder sb = new StringBuilder(super.toString());
		
		sb.append(" manufacturer: " + manufacturer + " topSpeed: " + topSpeed);
		return sb + "";
	}

}
