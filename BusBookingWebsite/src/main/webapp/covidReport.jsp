<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%@ taglib prefix="form"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%><link rel="stylesheet" type="text/css"
	href="css/general.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="holiday">
		<h1>Holiday Travels</h1>
		<p>
	</div>
	<h1>Nation wise result</h1>
	<br> total cases:${api.totalCases}
	<br> previous tests:${api.previousDayTests}
	<br> deaths:${api.deaths}
	<br> active cases:${api.activeCases}
	<br>
	<c:forEach var="regionData" items="${api.regionData}">

		<h3>region:${regionData.region}</h3>
activeCases:${regionData.activeCases}<br>
newInfected:${regionData.newInfected}<br>
recovered:${regionData.recovered}<br>
newRecovered:${regionData.newRecovered}<br>
deceased:${regionData.deceased}<br>
newDeceased:${regionData.newDeceased}<br>
totalInfected:${regionData.totalInfected}<br>
	</c:forEach>
	<br>
</body>
</html>