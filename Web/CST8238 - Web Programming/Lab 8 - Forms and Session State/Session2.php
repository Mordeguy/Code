<?php

session_start();



	include 'header.php';
	include 'menu.php';


	if(isset($_SESSION['firstName'])) {

		$firstName = $_SESSION["firstName"];
	}



	if(isset($_SESSION['lastName'])) {

			$lastName = $_SESSION['lastName'];
	}	

	
	if(isset($_SESSION['phoneNumber'])) {

			$phoneNumber = $_SESSION['phoneNumber'];
	}	


	if(isset($_SESSION['position'])) {

			$position = $_SESSION['position'];
	}	

	

	if(isset($_SESSION['multiple'])) {

			$multiple = $_SESSION['multiple'];
	}	






	echo "<div id= 'session2'>";
	echo "The name you entered in Session 1 is " . $firstName . " " . $lastName . "<br><br>";
	echo "Phone number inputted is: " . $phoneNumber . "<br><br>";
	echo "User position chosen is: " . $position . "<br><br>";
	echo "Game chosen is: " . $multiple;
	echo "</div>";



	

echo <<<_END
<html>
	<head>
		<title> Lab 8</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	<body>

	



			
	</body>
</html>
_END;

include 'footer.php';
?>