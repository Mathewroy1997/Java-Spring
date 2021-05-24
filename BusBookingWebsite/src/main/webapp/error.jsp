<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%= request.getAttribute("error") %>
<h2>Holiday Travels</h2>
<form action="login">
<input type="text" name="username" placeholder="username">
<input type="text" name="password" placeholder="password">

<input type="submit" value="login">
</form>
<form action="register">
<input type="submit" value="register">
</form>
</body>
</html>