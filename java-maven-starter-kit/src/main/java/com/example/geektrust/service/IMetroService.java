package com.example.geektrust.service;

public interface IMetroService {
	
	public void checkIn(String metroCardNumber, String passengerType, String station);
	public void printSummary();
}
