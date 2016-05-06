<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border=1 cellpadding=3 cellspacing=0>
        <tr>
          <th>Flight Number</th>
          <th>Number of seats</th>
        </tr>  
        
<%
int flight_Number = (int)request.getAttribute("FlightNumber");
int number_Seats = (int)request.getAttribute("NoOfSeats");
%>    

    <tr>
    	<td><%=flight_Number%> </td>
    	<td><%=number_Seats %></td>
    </tr> 
</table>

<a href="Index.html">Home</a>
</body>
</html>