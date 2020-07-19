<?php

session_start();

	if (isset($_POST["firstName"])) {
		$_SESSION["firstName"] = $_POST["firstName"];
		header("Location: Session2.php");
		
	}else{ 
		$firstName = "";
	}


	if (isset($_POST["lastName"])) {
		$_SESSION["lastName"] = $_POST["lastName"];
		header("Location: Session2.php");
		
	} else {
		$lastName = "";
	}


	if (isset($_POST["phoneNumber"])) {
		$_SESSION["phoneNumber"] = $_POST["phoneNumber"];
		header("Location: Session2.php");
	
	}else{
		$phoneNumber = "";
	}


	
	if (isset($_POST["position"])) { 
		$_SESSION["position"] = $_POST["position"];
		header("Location: Session2.php");
		
	}else{
		$position = "";
	}


	if (isset($_POST["multiple"])) {
		$_SESSION["multiple"] = $_POST["multiple"];
		header("Location: Session2.php");
		exit;
	}else{
		$multiple = "";
	}

	include 'header.php';
	include 'menu.php';
	

echo <<<_END
<html>
	<head>
		<title> Lab 8 - Session1.php</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	<body>

		<div id='left2'>
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




			
	</body>
</html>
_END;

include 'footer.php';
?>