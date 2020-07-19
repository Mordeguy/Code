<html>
	<head>
		<link rel='stylesheet' type='text/css' href='style.css' />
		<title>Lab 7</title>
	</head>

	<body>

<?php

	include 'header.php';
	

// Vehicle class ---------------------------------------------||

abstract class Vehicle {

	var $make;
	var $model;
	var $year;
	var $price;



	
	public function __construct($make, $model, $year,  $price) {
			$this->make = $make;
			$this->model = $model;
			$this->year = $year;
			$this->price = $price;
		}


	public function __toString() {

		return "Make: " . $this->make . "   Model: " . $this->model . "   Year: " . $this->year . "   Price: " . $this->price . " ";
	}

} // End of Vehicle Class




// LandVehicle Class -----------------------------------------||


class LandVehicle extends Vehicle {

	private $maxSpeed;


	public	function __construct($make, $model, $year, $price, $maxSpeed) {

			parent::__construct($make, $model, $year, $price);
			$this->maxSpeed = $maxSpeed;
	
	}

	
	public function __toString() {

		return parent::  __toString(). "   Speed Limit: " . $this->maxSpeed;

	}



} // End of LandVehicle Class




// LandVehicle Class -----------------------------------------||


class WaterVehicle extends Vehicle {

	private $boatCapacity;


	public	function __construct($make, $model, $year, $price, $boatCapacity) {

			parent::__construct($make, $model, $year, $price);
			$this->boatCapacity = $boatCapacity;
	
	}

	
	public function __toString() {

		return parent::  __toString(). "   Boat Capacity: " . $this->boatCapacity;

	}



} // End of LandVehicle Class

include 'menu.php';
	

		$vehicle = new LandVehicle("Toyota", "Camry", "1992", "2000","180");
		$vehicle2 = new LandVehicle("Honda", "Accord", "2002", "6000" , "200");
		$vehicle3 = new LandVehicle("Nissan", "Ultima", "2003", "2000" , "199");

		$vehicle4 = new WaterVehicle ("Mistubishi", "Turbo", "1999", "20000", "18");
		$vehicle5 = new WaterVehicle ("Hyundai", "XT", "2012", "26000", "18");
		$vehicle6 = new WaterVehicle ("Ford", "Turbo GT", "2005", "27000", "10");

		echo "<br><br><br><br><br><br>";
		echo $vehicle . "<br>";
		echo $vehicle2 . "<br>";
		echo $vehicle3 . "<br><br>";

		echo $vehicle4 . "<br>";
		echo $vehicle5 . "<br>";
		echo $vehicle6 . "<br>";
		echo "<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>";

	include 'footer.php';

?>



	</body>


</html>