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
Current Admins:
<table border="2" width="70%" cellpadding="2">  
<tr><th>Admin ID</th><th>Admin-Name</th><th>Username</th><th>Action</th></tr>  
   <c:forEach var="adminData" items="${adminList}"> 
     
   <tr>  
    
   <td>${adminData.adminID}</td>  
   <td>${adminData.adminName}</td> 
    <td>${adminData.username}</td> 
         
     
     <td><a href="admin/${adminData.adminID}">Delete Admin</a></td> 
      </tr>  
    
  
   </c:forEach>  
   </table>  

<p>
Add new Admins:<br>
<form action="addAdmin">
Admin Name:<input type="text" name="newAdminName" required>&emsp;
Username<input type="text" name="newAdminUsername" required>&emsp;
Password<input type="text" name="newAdminPassword" required>&emsp;

<input type="submit" value="Add New Admin">
</form>
</body>
</html>