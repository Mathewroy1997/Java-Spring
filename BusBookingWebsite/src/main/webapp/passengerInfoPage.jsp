<%! int i; %>
<%! int j; %> 
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Price=${totalPrice}<br>
Add passenger details:<br><p>
<c:forEach var = "i" begin = "1" end = "${tickets}">
         
          Passenger No.${i}<br>
          <form action="getpassenger">
Name<input type="text" name="name" placeholder="Name">
Age<input type="text" name="age" placeholder="Age">
ID No.<input type="text" name="id" placeholder="ID">
<input type="submit" value="add"></form>
        <br /><p>
      </c:forEach>
    
    <form action="confirmEntries">  <input type="submit" value="Submit"></form>
</body>
</html>