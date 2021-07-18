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
Current Routes:
<table border="2" width="70%" cellpadding="2">  
<tr><th>Route ID</th><th>Departure</th><th>Destination</th><th>Distance</th><th>Action</th></tr>  
   <c:forEach var="route" items="${routeDetailsList}"> 
     
   <tr>  
    
   <td>${route.routeId}</td>  
   <td>${route.departure}</td> 
    <td>${route.destination}</td> 
     <td>${route.totalDistanceInKm}</td>    
     
     <td><form action="deleteRouteDetails" method="post">
   <input type="submit" value="Delete Route">
   <input type="hidden" name="routeId" value=${route.routeId }>
   </form></td> 
      </tr>  
    
  
   </c:forEach>  
   </table>  
Add new Route:<br>   
 <form action ="addNewRouteDetails" method="post">

  Departure<input type="text" name="departure" required>
   Destination<input type="text" name="destination" required>
   Distance<input type="text" name="totalKm" required>
    
    <input type ="submit" value="Add Route">
 </form>
 
   
</body>
</html>