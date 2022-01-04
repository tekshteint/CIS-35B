<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.*" %>
<!DOCTYPE html>
<html>
<style>
table,th,td {
border:1px solid black
}
</style>
<head>
<meta charset="ISO-8859-1">
<%Automobile auto=(Automobile)request.getAttribute("data"); %>
<title><%out.println("Displaying configured "+auto.getMake()); %></title>
</head>
<body>

<table>
	<tr>
		<th>Make/Model</th>
		<%for (int i=0;i<auto.getOpset().size();i++) {%>
		<th><%out.println(auto.getOpsetName(i)); %>	</th>	
		<%} %>
		<th>Total Price</th>
	</tr>
	<tr>
		<td><%out.println(auto.getMake()+" "+auto.getModel());%></td>
		<%for (int i=0;i<auto.getOpset().size();i++){ %>
		<td> <%out.println(auto.getOptionChoice(auto.getOptionSetName(i))+" $"+auto.getOptionChoicePrice(auto.getOptionSetName(i)));%> </td>
		<% } %>
		<td><%out.println("$"+auto.getTotalPrice()); %></td>
	</tr>
</table>
<form action ="/lab6">
	<input type="submit" value = "Return Home" >
</form>
</body>
</html>