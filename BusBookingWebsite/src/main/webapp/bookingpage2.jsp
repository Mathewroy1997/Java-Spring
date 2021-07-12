<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><link rel="stylesheet" type="text/css" href="css/general.css">
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
</div>
<form action="findBus">

Departure: <%= request.getAttribute("departure") %>&emsp;
Destination: <%= request.getAttribute("destination") %>&emsp;
Price per seat:<%= request.getAttribute("rate") %>&emsp;
Available tickets:<%=request.getAttribute("seats") %>&emsp;
<p>
<br>Date: <input type="date" name="date">
<br>No.of tickets<input type="number" name="tickets" min="1" max="50"><br>

<input type="submit" value="Find bus"><br>
</form>
</body>
</html>