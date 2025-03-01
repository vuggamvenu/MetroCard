package com.example.geektrust.Entities;

import java.util.Map;

public class Station {

	private final String station;
	private final double totalRevenue;
	private final double totalDiscount;
	private Map<String, Integer> passengerCount;
	
	public Station(String station, double totalRevenue, double totalDiscount, Map<String, Integer> passengerCount) {
		super();
		this.station = station;
		this.totalRevenue = totalRevenue;
		this.totalDiscount = totalDiscount;
		this.passengerCount = passengerCount;
	}

	public String getStation() {
		return station;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public double getTotalDiscount() {
		return totalDiscount;
	}

	public Map<String, Integer> getPassengerCount() {
		return passengerCount;
	}

	@Override
	public String toString() {
		return "Station [station=" + station + ", totalRevenue=" + totalRevenue + ", totalDiscount=" + totalDiscount
				+ ", passengerCount=" + passengerCount + "]";
	}
	
	
	
}
