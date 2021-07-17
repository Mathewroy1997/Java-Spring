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
<form action="goToAddPassengerDetials" method="post">

Verify booking details:<p>
Date: ${date }&emsp;&emsp; Departure:${departure }&emsp;&emsp; Destination:${destination }&emsp;&emsp; Distance:${totalKm }<p>
BusID:${busId }&emsp;&emsp; Bus Category:${busType }&emsp;&emsp; Seats:${userTickets }&emsp;&emsp; Total Price:${totalPrice }<p>

<input type="submit" value="Fine, enter passenger details">

<input type="hidden" name="userTickets" value=${userTickets }  >
<input type="hidden" name="userId" value=${userId }  >
<input type="hidden" name="date" value=${date }  >
<input type="hidden" name="routeId" value=${routeId }  >
<input type="hidden" name="tripId" value=${tripId }  >
</form>
</body>
</html>