

<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/general.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="holiday">
		<h1>Holiday Travels</h1>
		<p>
	</div>
	<div class="topnav">
		<a href="logout">Sign-out</a>
	</div>
	Price=${totalPrice}
	<br>
	<br>
	<p>




		Add Passenger: <br>
	<form action="getpassenger" method="get">
	
	<input type="hidden" name="userId" value=${userId }>
	<input type="hidden" name="routeId" value=${routeId }>
	<input type="hidden" name="date" value=${date }>
	<input type="hidden" name="rate" value=${rate }>
	<input type="hidden" name="tripId" value=${tripId }>
	<input type="hidden" name="totalPrice" value=${totalPrice }>
	<input type="hidden" name="userTicket" value=${userTickets }>
	
		Name<input type="text" name="name" placeholder="Name"> 
		Age<input type="text" name="age" placeholder="Age"> 
		ID Proof No.<input type="text" name="id" placeholder="ID"> 
		
		<input type="submit"value="add">
		
	</form>
	<br />
	<p>
	<table border="2" width="70%" cellpadding="2">
		<tr>
			<th>Passenger Name</th>
			<th>Age</th>
			<th>Id Proof No.</th>
		</tr>
		<c:forEach var="passenger" items="${listTemporaryPassengers}">
			<tr>

				<td>${passenger.name}</td>
				<td>${passenger.age}</td>
				<td>${passenger.id}</td>
				
			</tr>
		</c:forEach>
	</table>




	<form action="confirmEntries" method="post">
	<input type="hidden" name="userId" value=${userId }>
<input type="hidden" name="routeId" value=${routeId }>
<input type="hidden" name="date" value=${date }>
<input type="hidden" name="rate" value=${rate }>
<input type="hidden" name="tripId" value=${tripId }>
<input type="hidden" name="totalPrice" value=${totalPrice }>
<input type="hidden" name="userTickets" value=${userTickets }>
		<input type="submit" value="Proceed to Payment">
	</form>
</body>
</html>