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
		<h1>Holiday Travels</h1>
		<p>
	</div>
	<div class="topnav">
		<a href="logout">Sign-out</a>
		</div>
Booking History
<table border="2" width="70%" cellpadding="2">  
<tr><th>Date</th><th>Departure</th><th>Destination</th><th>BusID</th><th>Bus Category</th><th>Passenger Name</th><th>Passenger Age</th><th>Passenger ID</th></tr>  
   <c:forEach var="userBookingHistory" items="${userBookingDetailsList}"> 
     
   <tr>  
    
   <td>${userBookingHistory.date}</td>  
   <td>${userBookingHistory.departure}</td> 
   <td>${userBookingHistory.destination}</td> 
    <td>${userBookingHistory.busId}</td> 
    <td>${userBookingHistory.busType}</td> 
     <td>${userBookingHistory.passengerName}</td> 
     <td>${userBookingHistory.passengerAge}</td>
     <td>${userBookingHistory.passengerId}</td>   
     
     
      </tr>  
    
  
   </c:forEach>  
   </table>  		

</body>
</html>