<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

Date<input type="date" name="Date"><br>
No.of tickets<input type="number" name="No.of people" min="1" max="10"><br>
<form action="findBus">
<input type="submit" value="Find bus"><br>
</form>
</body>
</html>