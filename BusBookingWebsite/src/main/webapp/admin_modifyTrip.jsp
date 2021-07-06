<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <link rel="stylesheet" type="text/css" href="css/general.css">
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
Current Buses:
<table border="2" width="70%" cellpadding="2">  
<tr><th>Trip ID</th><th>Date</th><th>Route ID</th><th>Seats</th><th>Action</th></tr>  
   <c:forEach var="trip" items="${tripTable}"> 
     
   <tr>  
    
   <td>${trip.tripID}</td>  
   <td>${trip.date}</td> 
    <td>${trip.routeID}</td> 
     <td>${trip.seats}</td>    
     
     <td><form action="deleteTrip"><input type="submit" value="Delete" name="trip"> </form></td> 
      </tr>  
    
  
   </c:forEach>  
   </table>  
  Add new trip:<br>
  <form action="addTrip">
  Trip ID:<input type="text" name="tripid"> Date:<input type="date" name="date">
  Route ID:<input type="text" name="routeid"> Seats: <input type="text" name ="seats"> 
   <br>
   <input type="submit" value="Add Bus">
   </form>
</body>
</html>