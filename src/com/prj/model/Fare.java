/**
 * 
 */
package com.prj.model;

/**
 * @author Pavan Kulkarni
 *
 * Fare.java
 */
public class Fare {
	
	private String flightNumber;
	private String fareCode;
	private double amount;
	private String restrictions;

	
	
	public Fare(String flightNumber, String fareCode, double amount,
			String restrictions) {
	
		this.flightNumber = flightNumber;
		this.fareCode = fareCode;
		this.amount = amount;
		this.restrictions = restrictions;
	}
	public Fare() {
		// TODO Auto-generated constructor stub
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getFareCode() {
		return fareCode;
	}
	public void setFareCode(String fareCode) {
		this.fareCode = fareCode;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getRestrictions() {
		return restrictions;
	}
	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}
	
	
}
