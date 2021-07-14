<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="css/general.css">
<%!int i = 0;%>
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
	Current Users:
	<table border="2" width="70%" cellpadding="2">
		<tr>
			<th>User ID</th>
			<th>Username</th>
			<th>Firstname</th>
			<th>Lastname</th>
			<th>E-mail</th>
			<th>Address</th>
			<th>Phone</th>
			<th>Action</th>
		</tr>
		<c:forEach var="customer" items="${userData}">

			<tr>

				<td>${customer.userid}</td>
				<td>${customer.username}</td>
				<td>${customer.firstname}</td>
				<td>${customer.lastname}</td>
				<td>${customer.email}</td>
				<td>${customer.address}</td>
				<td>${customer.phone}</td>

				<td><a href="customer/${customer.userid}">Delete UserData</a></td>
			</tr>


		</c:forEach>
	</table>
	Add new User:
	<br>
	<form action="addUserFromAdmin">

		First Name<input type="text" name="firstName"> Last Name<input
			type="text" name="lastName"> Username<input type="text"
			name="username"> Password<input type="text" name="password">
		E-Mail ID<input type="text" name="emailID"> Address<input
			type="text" name="address"> Phone<input type="text"
			name="phone">
<input type="submit" value="Add New User">

	</form>

</body>
</html>