<html>
	<head>
		<link rel='stylesheet' type='text/css' href='style.css' />
		
	</head>

	<body>
<?php
include 'header.php';
include 'menu.php';

	function allBottles() {

	$startNum = 99;
	$secondNum = 98;

	while ($secondNum >= 0)
	{
	echo "<div id='all'>$startNum Bottles of Beer on the Wall<br/>";
	echo "$startNum Bottles of Beer<br/>";
	echo "Take One Down, Pass it Around<br/>";
	echo "$secondNum Bottles of Beer on the Wall</div><br/><br/>";
	$startNum--;
	$secondNum--;
	}

	echo "There are no more bottles of beer!";
	


	}


	allBottles();
	
	
include 'footer.php';
	

	

?>

	</body>


</html>