<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page import="java.util.List"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="com.prj.model.Flight"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flight Information</title>
</head>
<body>
<a href="Index.html">Home </a>
 <table border=1 cellpadding=3 cellspacing=0>
        <tr>
          <th>FlightNumber</th>
          <th>Airline</th>
          <th>Weekdays</th>
          <th>Departure Airport</th>
          <th>Departure Time </th>
          <th>Arrival Airport</th>
          <th>Arrival Time </th>
        </tr>

<%
    List<Flight> list = new ArrayList<Flight>();  
    list = (List<Flight>)request.getAttribute("List");
    
    List<String> twoway = new ArrayList<String>();
    twoway = (List<String>)request.getAttribute("result");
    
    List<String> threeway = new ArrayList<String>();
    threeway = (List<String>)request.getAttribute("result2");
    
    
    if(list != null && list.size()>1){
    for(Flight fl:list)
    {
%>      <tr>
    	<td><%=fl.getFlightNumber()%></td>
        <td><%=fl.getAirline()%></td>  
        <td><%=fl.getWeekdays() %></td>
        <td><%=fl.getDepartureAirport()%></td>
        <td><%=fl.getDepartureTime() %></td>
        <td><%=fl.getArrivalAirport()%></td>
        <td><%=fl.getArrivalTime() %></td>
        </tr>
        
<% 
    }
    }
    if(twoway!=null && twoway.size()>0){
    for(String s:twoway)
    {
    	String [] split = s.split(",");
    	
 %>
    	<tr>
    	<td><%= split[0]%></td>
    	<td><%= split[1]%></td>
    	<td><%= split[2]%></td>
    	<td><%= split[3]%></td>
    	<td><%= split[4]%></td>
    	<td><%= split[5]%></td>
    	<td><%= split[6]%></td>
    	</tr>
    	<tr>
    	<td><%= split[7]%></td>
    	<td><%= split[8]%></td>
    	<td><%= split[9]%></td>
    	<td><%= split[10]%></td>
    	<td><%= split[11]%></td>
    	<td><%= split[12]%></td>
    	<td><%= split[13]%></td>
    	</tr>	
    	<tr><td> </hr></td></tr>
 <% 
    }
    if(threeway != null && threeway.size()>0)
    {
    	  for(String s:threeway)
    	    {
    	    	String [] split = s.split(",");
    	    	
  %>
    	    	 <tr>
    	    	<td><%= split[0]%></td>
    	    	<td><%= split[1]%></td>
    	    	<td><%= split[2]%></td>
    	    	<td><%= split[3]%></td>
    	    	<td><%= split[4]%></td>
    	    	<td><%= split[5]%></td>
    	    	<td><%= split[6]%></td>
    	    	</tr>
    	    	<tr>
    	    	<td><%= split[7]%></td>
    	    	<td><%= split[8]%></td>
    	    	<td><%= split[9]%></td>
    	    	<td><%= split[10]%></td>
    	    	<td><%= split[11]%></td>
    	    	<td><%= split[12]%></td>
    	    	<td><%= split[13]%></td>
    	    	</tr>	
    	    	<tr>
    	    	<td><%= split[14]%></td>
    	    	<td><%= split[15]%></td>
    	    	<td><%= split[16]%></td>
    	    	<td><%= split[17]%></td>
    	    	<td><%= split[18]%></td>
    	    	<td><%= split[19]%></td>
    	    	<td><%= split[20]%></td>
    	    	</tr>	
    	    	<tr><td> </hr></td></tr>
    	    	<tr><td> </hr></td></tr>
  <% 
     }
    }
  }
%>
 
</table>
</body>

</html>