<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
welcome back <%= request.getAttribute("username") %>. 

<h2>Holiday Travels</h2>
<input type="submit" value="Update profile"><br>
<input type="submit" value="View your tickets"><br>

Find your bus: <br>
Boarding point




<select name="departure" id="id">
    <option value="volvo"><%= request.getAttribute("d1") %></option>
    <option value="saab"><%= request.getAttribute("d2") %></option>
    <option value="opel"><%= request.getAttribute("d3") %></option>
    <option value="audi"><%= request.getAttribute("d4") %></option>


  </select><br>
  
  
Destination<select name="departure" id="id">
    <option value="volvo">Volvo</option>
    <option value="saab">Saab</option>
    <option value="opel">Opel</option>
    <option value="audi">Audi</option>


  </select>
Date<input type="date" name="Date"><br>
No.of tickets<input type="number" name="No.of people" min="1" max="10"><br>
<input type="submit" value="Find bus"><br>
</body>
</html>