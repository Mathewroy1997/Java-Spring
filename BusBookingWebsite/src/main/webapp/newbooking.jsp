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
Find your bus: <br>
<form action="nextPage">
Boarding point
<select  name="database1">
  <c:forEach items="${list}" var="route"  >
    <option value="${route.departure}" >
        ${route.departure}
    </option>
  </c:forEach>
</select>
<br>
Destination
<select name="database2" >
  <c:forEach items="${list2}" var="route1" >
    <option value="${route1.destination}">
        ${route1.destination}
    </option>
  </c:forEach>
</select>
<br>Date: <input type="date" name="date">

<input type="submit" value="Next:">
</form>
</body>
</html>