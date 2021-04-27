<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<body><header>
 <spring:url value="/resources/css/main.css" var="mainCss" />
    <spring:url value="/resources/js/jquery.1.10.2.min.js" var="jqueryJs" />
    <spring:url value="/resources/js/main.js" var="mainJs" />
    
    <link href="${mainCss}" rel="stylesheet" />
    <script src="${jqueryJs}"></script>
    <script src="${mainJs}"></script></header>
<form action="add">
<input type="text" name="t1"><br>
<input type="text" name="t2"><br>
<input type="submit">
</form>
</body>
</html>