/**
 * 
 */
package com.prj.model;


/**
 * @author Pavan Kulkarni
 *
 * Reservation.java
 */
public class Reservation {
	
	private String flightNumber;
	private String flightDate;
	private String seatNumber;
	private String customerName;
	private String phoneNumber;
	
	

	public Reservation(String flightNumber, String flightDate,
			String seatNumber, String customerName, String phoneNumber) {
		this.flightNumber = flightNumber;
		this.flightDate = flightDate;
		this.seatNumber = seatNumber;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the flightDate
	 */
	public String getFlightDate() {
		return flightDate;
	}
	/**
	 * @param flightDate the flightDate to set
	 */
	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}
	
	
	
	
	
}
