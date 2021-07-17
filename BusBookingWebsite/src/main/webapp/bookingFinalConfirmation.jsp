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

Date: ${date }&emsp;&emsp; Departure:${departure }&emsp;&emsp; Destination:${destination }&emsp;&emsp;
BusID:${busId }&emsp;&emsp; Bus Category:${busType }&emsp;&emsp;
<p>Passengers:
<table border="2" width="70%" cellpadding="2">  
<tr><th>Passenger Name</th><th>Age</th><th>ID proof No:</th></tr>  
   <c:forEach var="passenger" items="${temperoryPassengerList}"> 
     
   <tr>  
    
   <td>${passenger.passengerName}</td>  
   <td>${passenger.passengerAge}</td> 
    <td>${passenger.passengerId}</td> 
       
      
     
    
    


      </tr>  
    
  
   </c:forEach>  
   </table> 
   Total Price: ${totalPrice }
   <form action="toPayment" method="post">
   <input type="hidden" name="userTickets" value=${userTickets }  >
   <input type="hidden" name="tripId" value=${tripId }  >
   <input type="submit" value="Proceed To Payment">
   </form>
</body>
</html>