<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import = "model.*"%>
<!DOCTYPE html>
<html>
<head>
<style> table, th, td {border:1px solid black}</style>
<meta charset="ISO-8859-1">
<% Automobile auto = (Automobile)request.getAttribute("data"); 
String index= (String)request.getAttribute("carChoice");%>
<title>Configuring <%out.println(auto.getMake()+" "+auto.getModel()); %> </title>
<%int opsetSize=auto.getOpset().size(); %>
</head>
<body>
<form action="/lab6/FinishedAuto" method="post">
<table>
	<tr>
		<th>Make/Model</th>
		<%for (int i=0;i<auto.getOpset().size();i++) {%>
		<th><%out.println(auto.getOpsetName(i)); %>		
		<%} %>
	</tr>
	<tr>
		<td><%out.println(auto.getMake()+" "+auto.getModel());%></td>
		<%for (int i=0;i<auto.getOpset().size();i++){ %>
		<td><select name=<%out.println(auto.getOpsetName(i).replaceAll("\\s", ""));%> id=<%out.println(auto.getOpsetName(i).replaceAll("\\s", ""));%>>
			<%for (int j=0;j<auto.getOptions(i).size();j++) {%>
			<option value="<%out.println(auto.getOptionName(i, j)); %>" ><% out.println(auto.getOptionName(i, j)+" $"+auto.getOptionPrice(i, j));} %> </option>
		</select> 
		</td>
		<% } %>
	</tr>
</table>
<input type="hidden" id="carChoice" name="carChoice" value="<%=index %>">
<input type="submit" value = "Confirm Option Choices">
</form>
<form action ="/lab6">
	<input type="submit" value = "Return Home" ></form>
</body>
</html>