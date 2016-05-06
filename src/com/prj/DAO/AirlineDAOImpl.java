package com.prj.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.prj.model.Fare;
import com.prj.model.Flight;
import com.prj.model.Reservation;

public class AirlineDAOImpl implements AirLineDAO {

	private DataSource dataSource;
	static final Logger LOGGER = Logger.getLogger(AirlineDAOImpl.class);
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection con = null;

	public AirlineDAOImpl() {

		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}

	private static String getWeekdaysString( byte weekdays )
	{
		String days = "";
		byte sun = 1;
		byte mon = 2;
		byte tue = 4;
		byte wed = 8;
		byte thu = 16;
		byte fri = 32;
		byte sat = 64;

		if ( (weekdays & sun) != 0 )
			days += "Sun: ";
		if ( (weekdays & mon) != 0 )
			days += "Mon: ";
		if ( (weekdays & tue) != 0 )
			days += "Tue: ";
		if ( (weekdays & wed) != 0 )
			days += "Wed: ";
		if ( (weekdays & thu) != 0 )
			days += "Thu: ";
		if ( (weekdays & fri) != 0 )
			days += "Fri: ";
		if ( (weekdays & sat) != 0 )
			days += "Sat";

		return days;
	}
	@Override
	public List<Flight> getFlightDetails(String departureAirport,
			String arrivalAirport) {
		List<Flight> list = new ArrayList<Flight>();

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement("select * from flight where Departure_airport_code =? and Arrival_airport_code =?");
			ps.setString(1, departureAirport);
			ps.setString(2, arrivalAirport);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Flight(rs.getString(1), rs.getString(2), getWeekdaysString(rs.getByte(3)), rs.getString(4), rs.getString(5), rs
						.getString(6), rs.getString(7)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return list;
	}

	@Override
	public String getSeatCount(int FlightNumber, String date) {
		// TODO Auto-generated method stub
		int seatCount = 0;
		String flightnumberpluscount = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement("select count(*) as total_seats from seat_reservation where Flight_number = ? and Flight_Date = ?");
			ps.setInt(1, FlightNumber);
			ps.setString(2, date);
			rs = ps.executeQuery();
			
			rs.next();
			int total_available = rs.getInt("total_seats");
			System.out.println(total_available);

			ps = con.prepareStatement("select Total_number_of_seats from airplane A ,flight_instance F where A.Airplane_id = F.Airplane_id and F.Flight_number =? and F.Flight_Date =?");
			ps.setInt(1, FlightNumber);
			ps.setString(2, date);
			rs=ps.executeQuery();
			rs.next();
			int total_possible = rs.getInt("Total_number_of_seats");

			seatCount = total_possible - total_available;
			flightnumberpluscount = Integer.toString(FlightNumber) + "-"
					+ Integer.toString(seatCount);
			LOGGER.info(flightnumberpluscount);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return flightnumberpluscount;
	}
	

	@Override
	public List<Fare> getFareInformation(String FlightNumber) {
		// TODO Auto-generated method stub
		List<Fare> fares = new ArrayList<Fare>();
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(" select * from fare where flight_number =?");
			ps.setInt(1, Integer.parseInt(FlightNumber));
			rs = ps.executeQuery();

			while (rs.next()) {
				fares.add(new Fare(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return fares;
	}

	@Override
	public List<Reservation> getPassengerNames(String FlightNumber, String date) {
		// TODO Auto-generated method stub
		List<Reservation> passenger_manifesto = new ArrayList<>();

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(" select * from seat_reservation where Flight_number =? and Flight_date = ? ");
			ps.setInt(1, Integer.parseInt(FlightNumber));
			ps.setString(2, date);
			rs = ps.executeQuery();

			while (rs.next()) {
				passenger_manifesto.add(new Reservation(rs.getString(1), rs
						.getString(2), rs.getString(3), rs.getString(4), rs
						.getString(5)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return passenger_manifesto;
	}

	@Override
	public List<String> getFlightDetailsone(String departureAirport,
			String arrivalAirport) {
		// TODO Auto-generated method stub
		String query = "select flightone.Flight_number as flightone_Flight_number, flightone.Airline as flightone_Airline, flightone.Weekdays as flightone_Weekdays, flightone.Departure_airport_code as flightone_Departure_airport_code, flightone.Scheduled_departure_time as flightone_Scheduled_departure_time, flightone.Arrival_airport_code as flightone_Arrival_airport_code, flightone.Scheduled_arrival_time as flightone_Scheduled_arrival_time, ";
		query += "flighttwo.Flight_number as flighttwo_Flight_number, flighttwo.Airline as flighttwo_Airline, flighttwo.Weekdays as flighttwo_Weekdays, flighttwo.Departure_airport_code as flighttwo_Departure_airport_code, flighttwo.Scheduled_departure_time as flighttwo_Scheduled_departure_time, flighttwo.Arrival_airport_code as flighttwo_Arrival_airport_code, flighttwo.Scheduled_arrival_time as flighttwo_Scheduled_arrival_time ";
		query += "from flight flightone JOIN flight flighttwo ON flightone.Arrival_airport_code = flighttwo.Departure_airport_code where TIMEDIFF(flighttwo.Scheduled_departure_time, flightone.Scheduled_arrival_time) >= '01:00:00' and flightone.Departure_airport_code = ?  and flighttwo.Arrival_airport_code = ? limit 10";

		List<String> data = new ArrayList<>();
		String result = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, departureAirport);
			ps.setString(2, arrivalAirport);

			rs = ps.executeQuery();

			while (rs.next()) {
				String f1_flight_number = rs
						.getString("flightone_Flight_number");
				String f1_airline = rs.getString("flightone_Airline");
				byte f1_weekdays =rs.getByte("flightone_Weekdays");
				String f1_dept = rs
						.getString("flightone_Departure_airport_code");
				String f1_dep_time = rs
						.getString("flightone_Scheduled_departure_time");
				String f1_arr = rs.getString("flightone_Arrival_airport_code");
				String f1_arr_time = rs
						.getString("flightone_Scheduled_arrival_time");
				String f2_flight_number = rs
						.getString("flighttwo_Flight_number");
				String f2_airline = rs.getString("flighttwo_Airline");
				byte f2_weekdays = rs.getByte("flighttwo_Weekdays");
				String f2_dept = rs
						.getString("flighttwo_Departure_airport_code");
				String f2_dep_time = rs
						.getString("flighttwo_Scheduled_departure_time");
				String f2_arr = rs.getString("flighttwo_Arrival_airport_code");
				String f2_arr_time = rs
						.getString("flighttwo_Scheduled_arrival_time");
				
				byte intersection = (byte)(f1_weekdays & f2_weekdays );
				
				String weekdays = getWeekdaysString(intersection);
				
				if(!weekdays.isEmpty()){
				
				result = f1_flight_number + "," + f1_airline + ","
						+ weekdays + "," + f1_dept + "," + f1_dep_time;
				result = result + "," + f1_arr + "," + f1_arr_time + ","
						+ f2_flight_number + "," + f2_airline + ","
						+ weekdays + "," + f2_dept;
				result = result + "," + f2_dep_time + "," + f2_arr + ","
						+ f2_arr_time;
               
				data.add(result);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return data;
	}

	@Override
	public List<String> getFlightDetailstwo(String departureAirport,
			String arrivalAirport) {
		// TODO Auto-generated method stub
		List<String> data = new ArrayList<>();
		String result = null;
		
		String query = "select flightone.Flight_number as flightone_Flight_number,flightone.Airline as flightone_Airline,flightone.Weekdays as flightone_Weekdays, flightone.Departure_airport_code as flightone_Departure_airport_code, flightone.Scheduled_departure_time as flightone_Scheduled_departure_time, flightone.Arrival_airport_code as flightone_Arrival_airport_code, flightone.Scheduled_arrival_time as flightone_Scheduled_arrival_time, ";
		query = query +"flighttwo.Flight_number as flighttwo_Flight_number,flighttwo.Airline as flighttwo_Airline, flighttwo.Weekdays as flighttwo_Weekdays, flighttwo.Departure_airport_code as flighttwo_Departure_airport_code, flighttwo.Scheduled_departure_time as flighttwo_Scheduled_departure_time, flighttwo.Arrival_airport_code as flighttwo_Arrival_airport_code, flighttwo.Scheduled_arrival_time as flighttwo_Scheduled_arrival_time, ";
		query = query +"flightthree.Flight_number as flightthree_Flight_number,flightthree.Airline as flightthree_Airline,flightthree.Weekdays as flightthree_Weekdays,flightthree.Departure_airport_code as flightthree_Departure_airport_code,flightthree.Scheduled_departure_time as flightthree_Scheduled_departure_time,flightthree.Arrival_airport_code as flightthree_Arrival_airport_code,flightthree.Scheduled_arrival_time as flightthree_Scheduled_arrival_time ";
		query= query + "from flight flightone, flight flighttwo, flight flightthree where flightone.Arrival_airport_code = flighttwo.Departure_airport_code AND flighttwo.Arrival_airport_code = flightthree.Departure_airport_code AND TIMEDIFF(flighttwo.Scheduled_departure_time,flightone.Scheduled_arrival_time) >= '01:00:00' AND TIMEDIFF(flightthree.Scheduled_departure_time,flighttwo.Scheduled_arrival_time) >= '01:00:00'AND UPPER( flightone.Departure_airport_code ) = UPPER(?) AND UPPER( flightthree.Arrival_airport_code ) = UPPER(?) limit 10";
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, departureAirport);
			ps.setString(2, arrivalAirport);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				String flightone_flight_number = rs.getString( "flightone_Flight_number" );
				String flightone_airline = rs.getString( "flightone_Airline" );
				byte   flightone_weekdays = rs.getByte( "flightone_Weekdays" );
				String flightone_dept = rs.getString( "flightone_Departure_airport_code" );
				String flightone_dep_time = rs.getString( "flightone_Scheduled_departure_time" );
				String flightone_arr = rs.getString( "flightone_Arrival_airport_code" );
				String flightone_arr_time = rs.getString( "flightone_Scheduled_arrival_time" );
				String flighttwo_flight_number = rs.getString( "flighttwo_Flight_number" );
				String flighttwo_airline = rs.getString( "flighttwo_Airline" );
				byte   flighttwo_weekdays = rs.getByte( "flighttwo_Weekdays" );
				String flighttwo_dept = rs.getString( "flighttwo_Departure_airport_code" );
				String flighttwo_dep_time = rs.getString( "flighttwo_Scheduled_departure_time" );
				String flighttwo_arr = rs.getString( "flighttwo_Arrival_airport_code" );
				String flighttwo_arr_time = rs.getString( "flighttwo_Scheduled_arrival_time" );
				String flightthree_flight_number = rs.getString( "flightthree_Flight_number" );
				String flightthree_airline = rs.getString( "flightthree_Airline" );
				byte   flightthree_weekdays = rs.getByte( "flightthree_Weekdays" );
				String flightthree_dept = rs.getString( "flightthree_Departure_airport_code" );
				String flightthree_dep_time = rs.getString( "flightthree_Scheduled_departure_time" );
				String flightthree_arr = rs.getString( "flightthree_Arrival_airport_code" );
				String flightthree_arr_time = rs.getString( "flightthree_Scheduled_arrival_time" );

				String weekdays	 = getWeekdaysString( ( byte ) (flightone_weekdays & flighttwo_weekdays & flightthree_weekdays) );
				
				if(!weekdays.isEmpty()){
					
					result =               flightone_flight_number + "," + flightone_airline + ","+ weekdays + "," + flightone_dept + "," + flightone_dep_time + "," + flightone_arr + "," + flightone_arr_time ;
					result = result + ","+ flighttwo_flight_number + "," + flighttwo_airline + ","+ weekdays + "," + flighttwo_dept + "," + flighttwo_dep_time + "," + flighttwo_arr + "," + flighttwo_arr_time;
					result = result + ","+ flightthree_flight_number + "," + flightthree_airline + ","+ weekdays + "," + flightthree_dept + "," + flightthree_dep_time + "," + flightthree_arr + "," + flightthree_arr_time;
	               
					data.add(result);
					}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
		return data;
	}

	/* (non-Javadoc)
	 * @see com.prj.DAO.AirLineDAO#getFlightInstances(java.lang.String)
	 */
	@Override
	public List<Reservation> getFlightInstances(String passengername) {
		// TODO Auto-generated method stub
		List<Reservation> list = new ArrayList<>();
		String query = "select * from seat_reservation where Customer_name = UPPER(?)";
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, passengername);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				list.add(new Reservation(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return list;
	}

}
