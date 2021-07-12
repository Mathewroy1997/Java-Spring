<%@ page isELIgnored="false"%>
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
No trip available in selected date.<br>
<form action="findBus">
Available tickets:${seats}
<br>Date: <input type="date" name="date">
<br>No.of tickets<input type="number" name="tickets" min="1" max="50"><br>

<input type="submit" value="Find bus"><br>
</form>
</body>
</html>