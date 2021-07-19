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
	Current Buses:
	<table border="2" width="70%" cellpadding="2">
		<tr>
			<th>Bus ID</th>
			<th>Bus Category</th>
			<th>Rate/Km</th>
			<th>Total Seats</th>
			<th>Action</th>
		</tr>
		<c:forEach var="bus" items="${busDetailsList}">

			<tr>

				<td>${bus.busId}</td>
				<td>${bus.busType}</td>
				<td>${bus.busRatePerKm}</td>
				<td>${bus.busTotalSeats}</td>


				<td><form action="deleteBus" method="post">
						<input type="submit" value="Delete Bus"> <input
							type="hidden" name="busId" value=${bus.busId }>
					</form>
					<form action="editBus">
						<input type="submit" value="Edit Details"> <input
							type="hidden" name="busId" value=${bus.busId }>
					</form></td>
			</tr>


		</c:forEach>
	</table>

	Add new Bus:
	<br>
	<form action="addNewBus" method="post">


		Bus Category: <select name="busType">
			<c:forEach items="${busCategoryList}" var="busCategory">
				<option value="${busCategory.busCategory}">
					${busCategory.busCategory}</option>
			</c:forEach>
		</select> Rate per Km:<input type="number" min="0" name="ratePerKm" required>
		Total Seats: <input type="number" min="0" name="seats" required> <br>
		<input type="submit" value="Add Bus">
	</form>
	<form action="addNewBusFromNewCategory">
		<input type="submit" value="Add Bus with New Category">
	</form>
</body>
</html>