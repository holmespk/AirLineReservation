/**
 * 
 */
package com.prj.DAO;

import java.util.List;

import com.prj.model.Fare;
import com.prj.model.Flight;
import com.prj.model.Reservation;

/**
 * @author holmes
 * Mar 2, 2015 2015
 *
 * AirLineDAO.java
 */
public interface AirLineDAO {
	
	List<Flight> getFlightDetails(String departureAirport,String arrivalAirport);
	String getSeatCount(int FlightNumber,String date);
	List<Fare> getFareInformation(String FlightNumber);
	List<Reservation> getPassengerNames(String FlightNumber,String date);
	List<String> getFlightDetailsone(String departureAirport,String arrivalAirport);
	List<String> getFlightDetailstwo (String departureAirport,String arrivalAirport);
	List<Reservation> getFlightInstances (String passengername); 
	
}
