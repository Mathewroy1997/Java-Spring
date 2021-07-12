<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<link rel="stylesheet" type="text/css" href="css/general.css">
<%! int i = 0; %> 
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
</div>
Current Routes:
<table border="2" width="70%" cellpadding="2">  
<tr><th>Route ID</th><th>Departure</th><th>Destination</th><th>Rate</th><th>Action</th></tr>  
   <c:forEach var="route3" items="${routeTable}"> 
     
   <tr>  
    
   <td>${route3.routeID}</td>  
   <td>${route3.departure}</td> 
    <td>${route3.destination}</td> 
     <td>${route3.rate}</td>    
     
     <td><a href="route/${route3.routeID}">Delete Route</a></td> 
      </tr>  
    
  
   </c:forEach>  
   </table>  
Add new Route:<br>   
 <form action ="addRoute">
 Route ID<input type="text" name="routeid">
  Departure<input type="text" name="departure">
   Destination<input type="text" name="destination">
    Rate<input type="text" name="rate">
    
    <input type ="submit" value="add">
 </form>
   
</body>
</html>