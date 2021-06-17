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

         
          Passenger <br>
          <form action="getpassenger" >
          <c:forEach var = "temp" items="${temp}">
${temp.name}<input type="text" name="name" placeholder="Name">
${temp.age}<input type="text" name="age" placeholder="Age">
${temp.id}<input type="text" name="id" placeholder="ID">
<input type="submit" value="add">
        <br /><p>
      </c:forEach>
    </form>
    <form action="confirmEntries">  <input type="submit" value="Submit"></form>
</body>
</html>