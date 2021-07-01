<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%! int i = 0; %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Current Routes:
<table border="2" width="70%" cellpadding="2">  
<tr><th>Route ID</th><th>Departure</th><th>Destination</th><th>Rate</th><th>Action</th></tr>  
   <c:forEach var="route3" items="${routeTable}"> 
     
   <tr>  
    
   <td>${route3.routeID}</td>  
   <td>${route3.departure}</td> 
    <td>${route3.destination}</td> 
     <td>${route3.rate}</td>    
     
     <td><form action="deleteRoute"><input type="submit" value="Delete" name="route"  > </form></td> 
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