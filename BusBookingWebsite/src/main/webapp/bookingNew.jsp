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
<body><div class="holiday">
<h1>Holiday Travels</h1><p>

</div>
<div class="topnav">
<a href="logout" method="post">Sign-out</a>
</div>
Find your bus: <br>
<form action="checkRouteAndDateAvailability" method="post">
<input type="hidden" name="userId" value=${userId }>
Boarding point
<select  name="userSelectedDeparture">
  <c:forEach items="${departurePointsList}" var="route"  >
    <option value="${route.departure}" >
        ${route.departure}
    </option>
  </c:forEach>
</select>
<br>
Destination
<select name="userSelectedDestination" >
  <c:forEach items="${destinationPointsList}" var="route1" >
    <option value="${route1.destination}">
        ${route1.destination}
    </option>
  </c:forEach>
</select>
<br>Date: <input type="date" name="date">

<input type="submit" value="Next:">
</form>
</body>
</html>