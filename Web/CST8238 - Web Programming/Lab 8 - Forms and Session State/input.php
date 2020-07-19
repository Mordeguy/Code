<?php

session_start();

	if (isset($_POST["firstName"])) {
		$firstName = $_POST["firstName"];
		
	}else{ 
		$firstName = "";
	}

	if (isset($_POST["lastName"]))
		$lastName = $_POST["lastName"];
	else
		$lastName = "";


	if (isset($_POST["firstName"])) 
		$phoneNumber = $_POST["phoneNumber"];
	else
		$phoneNumber = "";

	
	if (isset($_POST["position"])) 
		$position = $_POST["position"];
	else
		$position = "";


	if (isset($_POST["multiple"])) 
		$multiple = $_POST["multiple"];
	else
		$multiple = "";


	include 'header.php';
	include 'menu.php';
	

echo <<<_END
<html>
	<head>
		<title> Lab 8</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	<body>

		<div id='left'>
			<form method="post">
				
				What is your first name?
				<input type="text" name="firstName" />
				<br><br>
			
				What is your last name?
				<input type="text" name="lastName" />
				<br><br>

				What is your phone number?
				<input type="text" name="phoneNumber" />
				<br><br>

				<input type="radio" name="position" value="Student" checked>Student
				<br>

				<input type="radio" name="position" value="Faculty">Faculty
				<br>
		
				<input type="radio" name="position" value="Teacher">Teacher
				<br><br>
	
		
				Click any of the games below
			
				<br>
				<select name="multiple">
					<option value="Call of Duty">Call of Duty</option>
					<option value="World of WarCraft">World of WarCraft</option>
					<option value="Sim City">Sim City</option>
					<option value="Zelda">Zelda</option>
				</select>
				<br><br>

				<input type="submit" value="Submit Information">



				

			</form>
		</div>

<div id='right'>

				The name you entered: $firstName $lastName <br><br><br>
				        Phone Number: $phoneNumber<br><br><br>
				       User Position: $position<br><br><br>
					 Game Chosen: $multiple

				
				</div>





			
	</body>
</html>
_END;

include 'footer.php';
?>