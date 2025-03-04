package com.example.geektrust.service;

import com.example.geektrust.Entities.MetroCard;
import com.example.geektrust.util.PassengerFares;

public class FareCalculatorService implements IFareCalculatorService{
	
	private final double farePercentageForReturnJourney = 0.5;
	private final double commissionPercentageForRecharge = 0.02;
	private final double discountPercentageForReturnJourney = 0.5;
	private final int noDiscount = 0;

	private final IMetroCardService metroCardService;
	
	public FareCalculatorService(IMetroCardService metroCardService) {
		this.metroCardService = metroCardService;
	}

	@Override
	public int findLeftBalance(int balance, String passengerType, boolean isReturnJourney) {
		int baseFare = PassengerFares.getFare(passengerType);
		double chargeForJourney = isReturnJourney ? baseFare * farePercentageForReturnJourney : baseFare;
		return  balance - (int)chargeForJourney;
	}

	@Override
	public double getCommision(String metroCardNumber, int leftBalance) {
		double commision = 0;
		if(leftBalance<0) {
			commision = Math.abs(leftBalance)*commissionPercentageForRecharge;
			MetroCard metroCardSaved = metroCardService.rechargeMetroCard(metroCardNumber, leftBalance);
		}
		return commision;
	}


	@Override
	public double calculateDiscount(String passengerType, boolean isReturnJourney) {
		int baseFare = PassengerFares.getFare(passengerType);
		return isReturnJourney ? baseFare * discountPercentageForReturnJourney : noDiscount;
	}

	@Override
	public double calculateRevenue(String passengerType, double commision, boolean isReturnJourney) {
		int baseFare = PassengerFares.getFare(passengerType);
		double afterDiscount = isReturnJourney ? (baseFare * farePercentageForReturnJourney) : baseFare;
		return afterDiscount + commision;
	}

	@Override
	public int getNewUpdateBalance(int balance, String passengerType, boolean isReturnJourney) {
		int baseFare = PassengerFares.getFare(passengerType);
		double chargeForJourney = isReturnJourney ? baseFare * farePercentageForReturnJourney : baseFare;
		return  balance - (int)chargeForJourney;
	}
	


}
