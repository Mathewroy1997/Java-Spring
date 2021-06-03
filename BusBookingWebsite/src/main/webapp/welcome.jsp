<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
welcome back <%= request.getAttribute("username") %>. 

<h2>Holiday Travels</h2>
<input type="submit" value="Update profile"><br>
<input type="submit" value="View your tickets"><br>

Find your bus: <br>
Boarding point

<select name="database1">
  <c:forEach items="${list}" var="route">
    <option value="${route.departure}">
        ${route.departure}
    </option>
  </c:forEach>
</select>
<br>
   Destination   <select name="database2">
  <c:forEach items="${list2}" var="route1">
    <option value="${route1.destination}">
        ${route1.destination}
    </option>
  </c:forEach>
</select>






<br>
<br>Date<input type="date" name="Date"><br>
No.of tickets<input type="number" name="No.of people" min="1" max="10"><br>
<input type="submit" value="Find bus"><br>
</body>
</html>