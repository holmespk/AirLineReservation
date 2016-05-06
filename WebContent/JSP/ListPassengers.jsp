<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.util.List"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="com.prj.model.Reservation"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of Passengers</title>
</head>
<body>
 <table border=1 cellpadding=3 cellspacing=0>
        <tr>
          <th>Flight Number</th>
          <th>Flight Date</th>
          <th>Seat</th>
          <th>Customer Name</th>
          <th>Phone Number</th>
        </tr>

<%
	List<Reservation> list = new ArrayList<Reservation>();  
	list = (List<Reservation>)request.getAttribute("Passengers");
	for(Reservation res:list)
	{
%>
	   <tr>
	    <td><%=res.getFlightNumber()%></td>
	    <td><%=res.getFlightDate()%></td>
	    <td><%=res.getSeatNumber()%></td>	
	    <td><%=res.getCustomerName()%></td>
	    <td><%=res.getPhoneNumber()%></td>
	   </tr>
<% 		
	}
%>
</table>
<a href="Index.html"><h3>Home</h3> </a>
</body>
</html>