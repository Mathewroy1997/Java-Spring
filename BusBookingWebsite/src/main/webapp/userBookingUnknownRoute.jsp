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
<body>
<div class="holiday">
<h1>Holiday Travels</h1><p>
</div>
<div class="topnav">
<a href="logout" method="post">Sign-out</a>

</div>
Sorry, No trip found. Change locations/date.
<input type="hidden" name="username" value=${username }>
<input type="hidden" name="userId" value="${userId }">
<form action="backToUserHome">
<input type="hidden" name="userId" value=${userId }>
<input type="hidden" name="username" value=${username }>
<input type="submit" value="Back To Home">
</form>
</body>
</html>