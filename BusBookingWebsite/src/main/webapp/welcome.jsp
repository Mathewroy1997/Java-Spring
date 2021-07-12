<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link rel="stylesheet" type="text/css" href="css/general.css">

<!DOCTYPE html>
<html>
<head>
<div class="holiday">
<h1>Holiday Travels</h1><p>

</div>
<div class="topnav">
<a href="logout" >Sign-out</a>
</div>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
welcome back <%= request.getAttribute("username") %>. <br>

<h2>Holiday Travels</h2>

<form action="bookingHistory">
<input type="submit" value="View your tickets">
</form>
<form action="bookingpage">
<input type="submit" value="Book New ticket">
</form>

</body>
</html>