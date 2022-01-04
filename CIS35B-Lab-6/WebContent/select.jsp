<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" 
import = "model.*"%>
<!DOCTYPE html>
<html>

<style>
table,th,td {
border:1px solid black
}
</style>
<head>
<meta charset="ISO-8859-1">
<title>Automobile PrintOuts</title>
</head>
<body>
	<h1>Displaying All Automobiles Available to be configured </h1>
	<% ArrayList<Automobile> autos = (ArrayList<Automobile>)request.getAttribute("data"); %>
		<form action=/lab6/Configure method="get">
		<%for (int i=0;i<autos.size();i++) {%>
			<input type="radio" name="CarSelection" id=<%=i+1 %> value=<%=i+1 %>>
			<label><%out.println(autos.get(i).getMake()+" "+autos.get(i).getModel());%></label><br>
		<%} %>
		<input type="submit" value = "Configure Selected Car">
		</form>
</body>
<form action ="/lab6">
	<input type="submit" value = "Return Home" >
</form>
</html>