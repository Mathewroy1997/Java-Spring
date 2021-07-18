<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <link rel="stylesheet" type="text/css" href="css/general.css">
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body><div class="holiday">
<h1>Holiday Travels</h1><p>
</div>
<div class="topnav">
<a href="logout" >Sign-out</a>
<a href="backToAdminHome" >Home</a>
</div>


<form action="updateBusDetails">
<input type="submit" value="Update Bus Details">
</form>
<form action="updateRouteDetails">
<input type="submit" value="Update Route">
</form>
<form action="updateTripDetails">
<input type="submit" value="Update Trip">
</form>
<form action="updateAdmin">
<input type="submit" value="Add Admin">
</form>
<form action="manageUsers">
<input type="submit" value="User Management">
</form>
<form action="adminBookingHistory">
<input type="submit" value="Booking History">
</form>
</body>
</html>