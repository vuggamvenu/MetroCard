package com.example.geektrust.service;

import java.util.HashMap;
import java.util.Map;

import com.example.geektrust.Entities.Station;
import com.example.geektrust.repository.IStationRepository;

public class StationService implements IStationService{
	
	private final int intialCountOfPassenger = 1; 
	
	private final IStationRepository stationRepository;
	private final IFareCalculatorService fareCalculatorService;

	public StationService(IStationRepository stationRepository, IFareCalculatorService fareCalculatorService) {
		this.stationRepository = stationRepository;
		this.fareCalculatorService = fareCalculatorService;
	}
	

	@Override
	public Station updateStationForReturnOrSingleJourney(String station, String passengerType, double commision, boolean isReturnJourney) {
		Station stationBean = stationRepository.findByStation(station);
		if(stationBean == null) {
			return createNewStation(station,passengerType,commision,isReturnJourney);
		}else {
			return updateExisitionStation(station, passengerType, commision, stationBean,isReturnJourney);
		}
	}


	private Station updateExisitionStation(String station, String passengerType, double commision,
		Station stationBean, boolean isReturnJourney) {
		Map<String ,Integer> passengerCount = createExistionPassengerCount(stationBean, passengerType);
		double totalRevenue = stationBean.getTotalRevenue() + fareCalculatorService.calculateRevenue(passengerType, commision, isReturnJourney);
		double totalDiscount = stationBean.getTotalDiscount() + fareCalculatorService.calculateDiscount(passengerType, isReturnJourney);
		return saveStation(station, totalRevenue, totalDiscount, passengerCount);
	}


	private Station createNewStation(String station, String passengerType, double commision, boolean isReturnJourney) {
		Map<String, Integer> passengerCount = createNewPassengerCount(passengerType);
		double revenue = fareCalculatorService.calculateRevenue(passengerType, commision,isReturnJourney);
		double discount = fareCalculatorService.calculateDiscount(passengerType,isReturnJourney);
		return saveStation(station, revenue, discount, passengerCount);
	}
	
	private Map<String, Integer> createNewPassengerCount(String passengerType) {
		Map<String, Integer> passengerCount = new HashMap<>();
		passengerCount.put(passengerType, intialCountOfPassenger);
		return passengerCount;
	}
	
	private Station saveStation(String station, double revenue, double discount, Map<String, Integer> passengerCount) {
		Station stationNewBean = new Station(station, revenue , discount, passengerCount);
		return stationRepository.save(stationNewBean);
	}
	
	private Map<String, Integer> createExistionPassengerCount(Station stationBean, String passengerType) {
		Map<String, Integer> passengerCount = stationBean.getPassengerCount();
		passengerCount.put(passengerType, passengerCount.getOrDefault(passengerType,0)+1);
		return passengerCount;
	}

}
