<html>
	<head>
		<link rel='stylesheet' type='text/css' href='style.css' />
		<title>Lab 7</title>
		
	</head>

	<body>

<?php

	include 'header.php';
	include 'menu.php';

	echo "<div id='content'>";
	echo "<h2>Output-1</h2>";

	$october = array();

	   $october["Monday"] = array('1st'=>3, '2nd'=>10, '3rd'=>17, '4th'=>24, '5th'=>31);
	   $october["Tuesday"] = array('1st'=>4, '2nd'=>11, '3rd'=>18, '4th'=>25);
           $october["Wednesday"] = array('1st'=>5, '2nd'=>12, '3rd'=>19, '4th'=>26);
           $october["Thursday"] = array('1st'=>6, '2nd'=>13, '3rd'=>20, '4th'=>27);
           $october["Friday"] = array('1st'=>7, '2nd'=>14, '3rd'=>21, '4th'=>28);
	   $october["Saturday"] = array('1st'=>8, '2nd'=>15, '3rd'=>22, '4th'=>29);
 	   $october["Sunday"] = array('1st'=>9, '2nd'=>16, '3rd'=>23, '4th'=>30);

	
	
	print_r($october);



	echo "<h2>Output-2</h2>";


	
	foreach ($october["Monday"] as $key => $value) {

		echo $value . " is the " . $key . " ";
		echo key($october). " in October.<br>";
	}

	echo "<br>";

	foreach ($october["Tuesday"] as $key => $value) {

		echo $value . " is the " . $key . " ";
		echo " Tuesday". " in October.<br>";
	}

	echo "<br>";

	foreach ($october["Wednesday"] as $key => $value) {

		echo $value . " is the " . $key . " ";
		echo " Wednesday". " in October.<br>";
	}

	echo "<br>";

	foreach ($october["Thursday"] as $key => $value) {

		echo $value . " is the " . $key . " ";
		echo " Thursday". " in October.<br>";
	}

	echo "<br>";

	foreach ($october["Friday"] as $key => $value) {

		echo $value . " is the " . $key . " ";
		echo " Friday". " in October.<br>";
	}

	echo "<br>";

	foreach ($october["Saturday"] as $key => $value) {

		echo $value . " is the " . $key . " ";
		echo " Saturday". " in October.<br>";
	}	

	echo "<br>";

	foreach ($october["Sunday"] as $key => $value) {

		echo $value . " is the " . $key . " ";
		echo " Sunday". " in October.<br>";
	}

	
	echo "<h2>Output-3</h2>";


	print_r(array_reverse($october));


	echo "<h2>Output-4</h2>";
	
	arsort($october, true);
	
	print_r($october);


	echo "<h2>Output-5</h2>";

	$october['Tuesday']['5th'] = 32;
	print_r($october);
	echo "<br>";


	echo "<br><br><br>";

	echo "</div>";
	include 'footer.php';
?>

	</body>

	


</html>