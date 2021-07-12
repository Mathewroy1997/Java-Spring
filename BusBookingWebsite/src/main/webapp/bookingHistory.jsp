<link rel="stylesheet" type="text/css" href="css/general.css">
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<table border="2" width="70%" cellpadding="2">  
<tr><th>Date</th><th>Departure</th><th>Destination</th><th>Tickets</th></tr>  
     <c:forEach var="view1" items="${list}">   
   <tr>  
   <td>${view1.date}</td>  
   <td>${view1.departure}</td> 
    <td>${view1.destination}</td>   
    <td>${view1.tickets}" </td>  
   </tr>  
   
       </c:forEach>
   </table> 
   <form action="check">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
   <a href="BusBookingWebsite/trialpage">check</a>
   </form>
</body>
</html>