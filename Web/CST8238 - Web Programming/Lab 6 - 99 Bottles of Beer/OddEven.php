<html>
	<head>
		<link rel='stylesheet' type='text/css' href='style.css' />
		
	</head>

	<body>
<?php
include 'header.php';
include 'menu.php';

	function divByThreeBottles() {

		$start = 99;
		$step = 1;
		

		echo "99 bottles of beer can serve an odd number of guests.<br/>";

		while ($step <= 99) {	

			$newNum = $start - $step;
			
			echo "<br/>You take $step down from $start...<br/>";
			echo "$newNum can serve ";

			if ($newNum % 2 == 0) {

				echo " can serve an EVEN number of guests.";

			} else {


				echo " can serve an ODD num ber of guests.";

			}

			$step++;
		
		}
		
		echo "<br/><br/>There are no more bottles of beer left!";

	}


	divByThreeBottles();
	
	
include 'footer.php';
	

	

?>

	</body>


</html>