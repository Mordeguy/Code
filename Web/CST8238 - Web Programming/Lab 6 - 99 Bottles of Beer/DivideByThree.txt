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
		while ($start > 0) {	
			

			echo "$start bottles of beer is";
		
			if ($start %3 == 0) {

			echo " a multiple of THREE!<br/><br/>";

			} else {

			echo " NOT a multiple of THREE!<br/><br/>";	
				
			}
		$start--;	
		}

	}


	divByThreeBottles();
	
	
include 'footer.php';
	

	

?>

	</body>


</html>