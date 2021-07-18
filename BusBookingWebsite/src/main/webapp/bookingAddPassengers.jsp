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
<form action="addPassengersToTemperoryTable" method="post" >

Add Passenger Details:<p>
<c:forEach var = "i" begin = "1" end = "${userTickets }">
        <p>Passenger: <c:out value = "${i}"/><p>
       Name <input type="text" name="passengerName" placeholder="Name" required>
       Age<input type="text" name="passengerAge" placeholder="Age" required>
       Id Proof No.<input type="text" name="passengerId" placeholder="ID" required>
      </c:forEach>
<p>*passengers must carry original ID proof during journey.
<p><input type="submit" value="Add Passengers">

<input type="hidden" name="userId" value=${userId }  >
<input type="hidden" name="date" value=${date }  >
<input type="hidden" name="routeId" value=${routeId }  >
<input type="hidden" name="tripId" value=${tripId }  >
<input type="hidden" name="departure" value=${departure }  >
<input type="hidden" name="destination" value=${destination }  >
<input type="hidden" name="busId" value=${busId }  >
<input type="hidden" name="busType" value=${busType }  >
<input type="hidden" name="totalPrice" value=${totalPrice }  >
<input type="hidden" name="userTickets" value=${userTickets }  >
</form>
</body>
</html>