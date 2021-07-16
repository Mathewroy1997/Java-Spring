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
Available Buses:<p>




<table border="2" width="70%" cellpadding="2">  
<tr><th>Bus ID</th><th>Category</th><th>Rate per Seat</th><th>Available Seats</th><th>Select seats:(Max. 5)</th><th>Action</th></tr>  
   <c:forEach var="availableBuses" items="${availableBusList}"> 
     
   <tr>  
    
   <td>${availableBuses.busId}</td>  
   <td>${availableBuses.busType}</td> 
    <td>${availableBuses.ratePerSeat}</td> 
     <td>${availableBuses.availableSeats}</td>    
     
     <td><input type="number" name="userTickets" min="1" max="5"></td> 
     <td></td>
      </tr>  
    
  
   </c:forEach>  
   </table>  

</body>
</html>