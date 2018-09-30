<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Reservation Page</title>
</head>
<script type="text/javascript">
	function populateDefaultValues() {
		// body...
		document.getElementById('reservationDate').valueAsDate = new Date();
	}
</script>

<body>
<h1>Reservation Page</h1>
<div>
		<form name="reservationForm" action="ReservationController" method="post" 
		onload="populateDefaultValues()" 
		onsubmit="return validateReserve()">
				
			<label for="parkingLot">Parking Lot </label><br><input type="text" name="lotId" id="lotId"><br><br>
			<label for="parkingSpot">Parking Spot </label><br><input type="text" name="spotId" id="spotId"><br><br>
			<label for="reservationDate">Reservation Date </label><br><input type="date" name="reservationDate" id="reservationDate"><br><br>
			
			<input type="submit" name="submit" value="reserve">
			<input type="reset" name="clear">
		</form>
</div>
</body>
</html>