<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<div class="topnav">
<a href="logout" >Sign-out</a>

</div>

Date=${date }&emsp;&emsp;
Departure:${departure }&emsp;&emsp;
Destination:${destination }&emsp;&emsp;
Total Distance:${totalKm }km&emsp;&emsp;
<p>
Available Buses:


<table border="2" width="70%" cellpadding="2">  
<tr><th>Bus ID</th><th>Category</th><th>Rate per Seat</th><th>Available Seats</th><th>Select seats:(Max. 5)</th><th>Action</th></tr>  
   <c:forEach var="availableBuses" items="${availableBusList}"> 
     
   <tr>  
    
   <td>${availableBuses.busId}
   <input type="hidden" name="tripId" value=${availableBuses.tripId }>
   </td>  
   <td>${availableBuses.busType}</td> 
    <td>${availableBuses.ratePerSeat}</td> 
     <td>${availableBuses.availableSeats}</td>    
      <form action="passUserEntries" method="post">
     <td><input type="number" name="userTickets" min="1" max="5"></td> 
     <td>
    <input type="hidden" name="username" value=${username }>
     <input type="hidden" name="userId" value=${userId }>
     <input type="hidden" name="date" value=${date }>
      <input type="hidden" name="routeId" value=${routeId }>
     <input type="hidden" name="departure" value=${departure }>
     <input type="hidden" name="destination" value=${destination }>
     <input type="hidden" name="totalKm" value=${totalKm }>
     <input type="hidden" name="busId" value=${availableBuses.busId }>
     <input type="hidden" name="busType" value=${availableBuses.busType }>
     <input type="hidden" name="ratePerSeat" value=${availableBuses.ratePerSeat }>
     <input type="hidden" name="availableSeats" value=${availableBuses.availableSeats }>
     <input type="hidden" name="tripId" value=${availableBuses.tripId }>
     <input type="hidden" name="userTickets"  >
     
     <input type="submit" value="Book Now">
     </form>

</td>
      </tr>  
    
  
   </c:forEach>  
   </table>  
<form action="backToUserHome">
<input type="hidden" name="userId" value=${userId }>
<input type="hidden" name="username" value=${username }>
<input type="submit" value="Back To Home">
</form>
</body>
</html>