<html>
	<head>
		<link rel='stylesheet' type='text/css' href='style.css' />
		
	</head>

	<body>

<?php
include 'header.php';
include 'menu.php';
?>

<form>

  Range 1:
  <input type="text" name="range1">
  <br>
  Range 2:
  <input type="text" name="range2">
  <br><br>
  <input type="submit" name="submit" value="submit">
</form>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
	
<?php

	 function randomBottles() {

		
		
	
		if (isset ($_GET['submit'])) {	
	 	     $range1= $_GET['range1'];
          	     $range2= $_GET['range2'];

		if ($range1 !=0 && $range2 != 0) {
		     $number = rand($range1, $range2);
		     echo "The Web Server has selected the random number $number from the range $range1 and $range2. <br/>";
		     echo "$number Bottle of Beer can serve";

			if ($number % 2 == 0) {

				echo " an EVEN number of guests.";

			} else {

				echo " an ODD number of guests.";

			}
		}
		}

		
	}


	randomBottles();

	include 'footer.php';

?>

	</body>


</html>