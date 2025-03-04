package com.example.geektrust.service;

public interface IFareCalculatorService {

	int findLeftBalance(int balance, String passengerType, boolean b);

	double getCommision(String metroCardNumber, int leftBalance);

	double calculateRevenue(String passengerType, double commision, boolean isReturnJourney);

	double calculateDiscount(String passengerType, boolean isReturnJourney);

	int getNewUpdateBalance(int balance, String passengerType, boolean isReturnJourney);


}
