<%! int i; %>
<%! int j; %> 
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <link rel="stylesheet" type="text/css" href="css/general.css">
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
Price=${totalPrice}<br>
<br><p>




         Add Passenger: <br>
          
          <form action="getpassenger">
Name<input type="text" name="name" placeholder="Name">
Age<input type="text" name="age" placeholder="Age">
ID Proof No.<input type="text" name="id" placeholder="ID">
<input type="submit" value="add"></form>
<br /><p>
      
      
<table border="2" width="70%" cellpadding="2">  
<tr><th>Name</th><th>Age</th><th>Id</th></tr>  
   <c:forEach var="temp1" items="${listtemppass}">   
   <tr>  
    
   <td>${temp1.name}</td>  
   <td>${temp1.age}</td> 
    <td>${temp1.id}</td>     
   </tr>  
   </c:forEach>  
   </table>  


         
          
    <form action="confirmEntries">  <input type="submit" value="Proceed to Payment"></form>
</body>
</html>