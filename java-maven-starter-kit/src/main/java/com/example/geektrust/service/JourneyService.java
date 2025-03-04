package com.example.geektrust.service;

import com.example.geektrust.Entities.Journey;
import com.example.geektrust.Entities.MetroCard;
import com.example.geektrust.Entities.Station;
import com.example.geektrust.repository.IJourneyRepository;
import com.example.geektrust.util.PassengerFares;

public class JourneyService implements IJourneyService{

	private final IMetroCardService metroCardService;
	private final IJourneyRepository journeyRepository;
	private final IFareCalculatorService fareCalculatorService;
	private final IStationService stationService;
	
	public JourneyService(IJourneyRepository journeyRepository, IMetroCardService metroCardService, IFareCalculatorService fareCalculatorService, IStationService stationService) {
		this.journeyRepository = journeyRepository;
		this.metroCardService = metroCardService;
		this.fareCalculatorService = fareCalculatorService;
		this.stationService = stationService;
	}

	@Override
	public void processJourney(String metroCardNumber, String passengerType, String station) {
		MetroCard metroCard = metroCardService.getMetroCard(metroCardNumber);
		Journey journey = new Journey(metroCardNumber, station, passengerType, true);
		Journey previousJourney = journeyRepository.isReturnJourney(metroCardNumber);
		
		if(isReturnJourney(previousJourney)) {
			processReturnJourney(metroCard, passengerType, station);
		}else {
			processSingleJourney(metroCard, passengerType, station, journey);
		}
	}
	
	private void processSingleJourney(MetroCard metroCard, String passengerType, String station, Journey journey) {

		int leftBalance = fareCalculatorService.findLeftBalance(metroCard.getBalance(), passengerType, false);
		double commision= fareCalculatorService.getCommision(metroCard.getMetroCardNumber(),leftBalance);
		updateJouney(metroCard.getMetroCardNumber(), station, passengerType,true);
		stationService.updateStationForReturnOrSingleJourney(station, passengerType, commision, false);
		MetroCard updatedMetroCardAfterDeductingCharges = dedcutChargesFromCard(metroCard,passengerType,false);
	}
	
	private void processReturnJourney(MetroCard metroCard, String passengerType, String station) {
		int leftBalance = fareCalculatorService.findLeftBalance(metroCard.getBalance(), passengerType, true);
		double commision= fareCalculatorService.getCommision(metroCard.getMetroCardNumber(),leftBalance);
		updateJouney(metroCard.getMetroCardNumber(), station, passengerType, false);
		Station updatedStationBean = stationService.updateStationForReturnOrSingleJourney(station, passengerType, commision, true);
		MetroCard updatedMetroCardAfterDeductingCharges = dedcutChargesFromCard(metroCard,passengerType,true);
	}

	private MetroCard dedcutChargesFromCard(MetroCard metroCard, String passengerType,boolean isReturnJourney) {
		MetroCard newMetroCard = metroCardService.getMetroCard(metroCard.getMetroCardNumber());
		int updatedBalance = fareCalculatorService.getNewUpdateBalance(newMetroCard.getBalance(),passengerType,isReturnJourney);
		return metroCardService.updateMetroCard(newMetroCard.getMetroCardNumber(), updatedBalance);
	}

	private void updateJouney(String metroCardNumber, String station, String passengerType, boolean makingRetrnJourney) {
		Journey journeyMakingFalse = new Journey(metroCardNumber, station, passengerType, makingRetrnJourney);
		journeyRepository.save(journeyMakingFalse);
	}

	//return true if it is return journey
	private boolean isReturnJourney(Journey journeyNew) {
		return journeyNew != null && journeyNew.isReturnJourney();
	}

}
