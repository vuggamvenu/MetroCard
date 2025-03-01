package com.example.geektrust.Entities;

public class Journey {
	private final String metroCardNumber;
	private final String station;
	private final String passengerType;
	private final boolean isReturnJourney;
	
	
	public Journey(String metroCardNumber, String station, String passengerType, boolean isReturnJourney) {
		super();
		this.metroCardNumber = metroCardNumber;
		this.station = station;
		this.passengerType = passengerType;
		this.isReturnJourney = isReturnJourney;
	}


	public String getMetroCardNumber() {
		return metroCardNumber;
	}


	public String getStation() {
		return station;
	}


	public String getPassengerType() {
		return passengerType;
	}


	public boolean isReturnJourney() {
		return isReturnJourney;
	}
	
	
}
