<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%><link rel="stylesheet" type="text/css" href="css/general.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<form action="submitNewRegistraion">
<body><div class="holiday">
<h1>Holiday Travels</h1><p>

</div>
<div class="topnav">
<a href="logout" >Sign-out</a>
</div>
${duplicateMessage }<p>
 Enter username: <input type="text" name="username" required><br>
Enter password: <input type="text" name="password" required><br>
Enter first name: <input type="text" name="firstName" required><br>
Enter last name: <input type="text" name="lastName" required><br>
Enter email address: <input type="text" name="emailId" required><br>
Enter address:  <input type="text" name="address" required><br>
Enter mob.number:<input type="text" name="phone" required><br>
                         <input type="submit"><br>
                         </form>
</body>
</html>




