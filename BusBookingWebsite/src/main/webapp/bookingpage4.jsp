<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
No trip available in selected date.<br>
<form action="findBus">
Available tickets:${seats}
<br>Date: <input type="date" name="date">
<br>No.of tickets<input type="number" name="tickets" min="1" max="50"><br>

<input type="submit" value="Find bus"><br>
</form>
</body>
</html>