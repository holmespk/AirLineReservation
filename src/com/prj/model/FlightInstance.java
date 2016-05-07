/**
 * 
 */
package com.prj.model;


import java.sql.Time;
import java.util.Date;

/**
 * @author Pavan Kulkarni
 * 
 *
 * LegInstance.java
 */
public class FlightInstance {
	
	private String flightNumber;
	private Date date;
	private int noOfAvailableSeats;
	private int planeID;
	private Time departureTime;
	private Time arrivalTime;
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public Date getLegdate() {
		return date;
	}
	public void setLegdate(Date legdate) {
		this.date = legdate;
	}
	public int getNoOfAvailableSeats() {
		return noOfAvailableSeats;
	}
	public void setNoOfAvailableSeats(int noOfAvailableSeats) {
		this.noOfAvailableSeats = noOfAvailableSeats;
	}
	public int getPlaneID() {
		return planeID;
	}
	public void setPlaneID(int planeID) {
		this.planeID = planeID;
	}
	
	public Time getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	
	
	
	
	
}
