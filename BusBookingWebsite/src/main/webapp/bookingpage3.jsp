<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
Confirmation page<br>

Departure: <%= request.getAttribute("departure") %>&emsp;
Destination: <%= request.getAttribute("destination") %>&emsp;
Price per seat:<%= request.getAttribute("rate") %>&emsp;
Available seats:<%=request.getAttribute("seats") %>&emsp;
<form action ="get">
<br>No.of tickets<input type="number" name="tickets" min="1" max="50"><br>
<input type="submit" value="Book Ticket "><br>
</form>
</body>
</html>