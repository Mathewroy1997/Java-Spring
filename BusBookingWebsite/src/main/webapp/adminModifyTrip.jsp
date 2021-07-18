<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<link rel="stylesheet" type="text/css" href="css/general.css">

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
<a href="backToAdminHome" >Home</a>
</div>
Current Trips:
<table border="2" width="70%" cellpadding="2">  
<tr><th>Trip ID</th><th>Date</th><th>RouteID</th><th>BusID</th><th>Seats</th><th>Action</th></tr>  
   <c:forEach var="trip" items="${tripDetailsList}"> 
  
     
   <tr>  
    
   <td>${trip.tripId}</td>  
   <td>${trip.date}</td> 
    <td>${trip.routeId}</td> 
     <td>${trip.busId}</td> 
     <td>${trip.availableSeats}</td>    
     
     <td><form action="deleteTripDetails" method="post">
   <input type="submit" value="Delete Trip">
   <input type="hidden" name="tripId" value=${trip.tripId }>
   </form>
   </td>
    
   </tr>  
   </c:forEach>  
   </table>  

Add Trip:
<form action="addNewTripDetails" method="post">

Date:<input type="date" name="date" value="Date">
RouteID: <select  name="routeId">
  <c:forEach items="${routeDetailsList}" var="route"  >
    <option value="${route.routeId}" >
        ${route.routeId}
    </option>
  </c:forEach>
</select>
BusId:
<select  name="busId">
  <c:forEach items="${busDetailsList}" var="bus"  >
    <option value="${bus.busId}" >
        ${bus.busId}
    </option>
  </c:forEach>
</select>
Seats:<input type="text" placeholder="Seats" name="seats" required>
<input type="submit" value="Add Trip">
</form>

</body>
</html>