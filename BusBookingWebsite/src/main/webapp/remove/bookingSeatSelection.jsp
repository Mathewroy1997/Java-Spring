<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><link rel="stylesheet" type="text/css" href="css/general.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body><div class="holiday">
<h1>Holiday Travels</h1><p>

</div>
<div class="topnav">
<a href="logout" >Sign-out</a>
</div> 
Confirmation page<br>

Departure: <%= request.getAttribute("departure") %>&emsp;
Destination: <%= request.getAttribute("destination") %>&emsp;
Price per seat:<%= request.getAttribute("rate") %>&emsp;
Available seats:<%=request.getAttribute("seats") %>&emsp;
<form action ="bookingGetPassengerInfo" >

<input type="hidden" name="userId" value=${userId }>
<input type="hidden" name="routeId" value=${routeId }>
<input type="hidden" name="date" value=${date }>
<input type="hidden" name="rate" value=${rate }>
<input type="hidden" name="tripId" value=${tripId }>

<br>No.of tickets<input type="number" name="userEntryTickets" min="1" max="50"><br>
<input type="submit" value="Book Ticket "><br>
</form>
</body>
</html>