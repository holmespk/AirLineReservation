<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="com.prj.model.Fare"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fare Information</title>
</head>
<body>
 <table border=1 cellpadding=3 cellspacing=0>
        <tr>
          <th>Flight Number</th>
          <th>Fare Code</th>
          <th>Amount</th>
          <th>Restrictions</th>
        </tr>

<%
	List<Fare> fareInfo = new ArrayList<>();
    fareInfo= (List<Fare>) request.getAttribute("fareInfo");
	for(Fare f :fareInfo)
	{
%>
	   <tr>
	    <td><%=f.getFlightNumber()%></td>
	   <td><%=f.getFareCode()%></td>
	   <td><%=f.getAmount()%></td>	
	   <td><%=f.getRestrictions()%></td>
	   </tr>
<% 		
	}
%>
</table>
<a href="Index.html"><h3>Home</h3> </a>
</body>
</html>