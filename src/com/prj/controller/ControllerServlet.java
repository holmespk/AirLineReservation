/**
 * 
 */
package com.prj.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.prj.DAO.AirlineDAOImpl;
import com.prj.model.Fare;
import com.prj.model.Flight;
import com.prj.model.Reservation;

/**
 * @author holmes
 * Mar 2, 2015 
 *
 * ControllerServlet.java
 */
public class ControllerServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(ControllerServlet.class);

	private AirlineDAOImpl dao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		dao = new AirlineDAOImpl();
	}



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action=request.getParameter("action");
		LOGGER.info(action);
		RequestDispatcher dispatcher;
		
		if(action.equalsIgnoreCase("getFlightInfo"))
		{
			String arrivalAirport = request.getParameter("acode");
			String departureAirport = request.getParameter("dcode");
			
			String direct = request.getParameter("direct");
			
			
			if(direct.equals("direct"))
			{
			List<Flight> list = dao.getFlightDetails(departureAirport, arrivalAirport);
			request.setAttribute("List", list);
			
			dispatcher = request.getRequestDispatcher("/JSP/FlightInfo.jsp");
			dispatcher.forward(request, response);
			}
			
			else if(direct.equals("onestop"))
			{
				List<Flight> list = dao.getFlightDetails(departureAirport, arrivalAirport);
				request.setAttribute("List", list);
				
				List<String> result= dao.getFlightDetailsone(departureAirport, arrivalAirport);
				request.setAttribute("result", result);
				dispatcher = request.getRequestDispatcher("/JSP/FlightInfo.jsp");
				dispatcher.forward(request, response);
			}
			else
			{
				List<Flight> list = dao.getFlightDetails(departureAirport, arrivalAirport);
				request.setAttribute("List", list);
				
				List<String> result= dao.getFlightDetailsone(departureAirport, arrivalAirport);
				request.setAttribute("result", result);
				LOGGER.info(result);
				
				List<String> result2= dao.getFlightDetailstwo(departureAirport, arrivalAirport);
				request.setAttribute("result2", result2);
				
				dispatcher = request.getRequestDispatcher("/JSP/FlightInfo.jsp");
				dispatcher.forward(request, response);
			}
		}

		if(action.equalsIgnoreCase("getNoOfSeats"))
		{
			String FLightNumber = request.getParameter("fnumber");
			String date = request.getParameter("date");	
			String data = dao.getSeatCount(Integer.parseInt(FLightNumber),  date);
			String [] s = data.split("-");
			
			int number_of_seats = Integer.parseInt(s[1]);
			int flightNumber = Integer.parseInt(s[0]);
			request.setAttribute("FlightNumber", flightNumber);
			request.setAttribute("NoOfSeats", number_of_seats);
			dispatcher =request.getRequestDispatcher("/JSP/SeatAvailability.jsp");
			dispatcher.forward(request, response);
		}
		
		if(action.equalsIgnoreCase("getFareInfo"))
		{
			String FLightNumber = request.getParameter("fnumber");
			
			List<Fare> fareInfo = dao.getFareInformation(FLightNumber);
			request.setAttribute("fareInfo", fareInfo);
			dispatcher =request.getRequestDispatcher("/JSP/FareInformation.jsp");
			dispatcher.forward(request, response);
		}
		
		
		if(action.equalsIgnoreCase("getPassengerList"))
		{
			String FLightNumber = request.getParameter("fnumber");
			String FlightDate = request.getParameter("date");
			
			List<Reservation> passengers = dao.getPassengerNames(FLightNumber, FlightDate);
			request.setAttribute("Passengers", passengers);
			dispatcher = request.getRequestDispatcher("/JSP/ListPassengers.jsp");
			dispatcher.forward(request, response);
		}
		
		if(action.equalsIgnoreCase("getAllInstance"))
		{
			String name = request.getParameter("name");
			
			List<Reservation> instances = dao.getFlightInstances(name);
			request.setAttribute("Instances", instances);
			dispatcher = request.getRequestDispatcher("/JSP/AllFlightInstance.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	
}
