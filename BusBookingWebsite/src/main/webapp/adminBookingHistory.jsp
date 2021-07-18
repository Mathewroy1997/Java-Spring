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
<body><div class="holiday">
<h1>Holiday Travels</h1><p>
</div>
<div class="topnav">
<a href="logout" >Sign-out</a>
<a href="backToAdminHome" >Home</a>
</div>
Booking History
<table border="2" width="70%" cellpadding="2">  
<tr><th>UserID</th><th>Date</th><th>TripID</th><th>Passenger Name</th><th>Passenger Age</th><th>Passenger ID</th></tr>  
   <c:forEach var="adminBookingHistory" items="${bookingDetailsList}"> 
     
   <tr>  
    
   <td>${adminBookingHistory.userId}</td>  
   <td>${adminBookingHistory.date}</td> 
   <td>${adminBookingHistory.tripId}</td> 
    
     <td>${adminBookingHistory.passengerName}</td> 
     <td>${adminBookingHistory.passengerAge}</td>
     <td>${adminBookingHistory.passengerId}</td>   
     
     
      </tr>  
    
  
   </c:forEach>  
   </table>  	
</body>
</html>