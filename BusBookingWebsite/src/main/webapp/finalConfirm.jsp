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
<body>
<table border="2" width="70%" cellpadding="2">  
<tr><th>User Id</th><th>Name</th><th>Age</th><th>Id</th></tr>  
   <c:forEach var="passenger" items="${passenger}">   
   <tr>  
   <td>${passenger.userid}</td>  
   <td>${passenger.name}</td>  
   <td>${passenger.age}</td> 
    <td>${passenger.id}</td>     
   </tr>  
   </c:forEach>  
   </table>  
   <br/>
</body>
</html>