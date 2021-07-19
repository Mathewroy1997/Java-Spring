<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link rel="stylesheet" type="text/css" href="css/general.css">

<!DOCTYPE html>
<html>
<head>
<div class="holiday">
	<h1>Holiday Travels</h1>
	<p>
</div>
<div class="topnav">
	<a href="logout">Sign-out</a> <a href="backToAdminHome">Home</a>
</div>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="changeBusDetails" method="post">
		Bus ID:${busId }&emsp;Bus Category:${busType }
		<p>
			New Rate Per KM:<input type="number" min="0" name="ratePerKm"
				placeholder="Rate">
		<p>
			New Seats:<input type="number" min="0" name="seats" placeholder="seats">
			<input type="submit" value="Change values"> <input
				type="hidden" name="busId" value=${busId }>
	</form>
</body>
</html>