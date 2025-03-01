package com.example.geektrust.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.geektrust.Entities.Journey;
import com.example.geektrust.Entities.MetroCard;
import com.example.geektrust.Entities.Station;
import com.example.geektrust.repository.IJourneyRepository;
import com.example.geektrust.repository.IMetroCardRepository;
import com.example.geektrust.repository.IStationRepository;
import com.example.geektrust.util.PassengerFares;

public class MetroService implements IMetroService{
	
	private final double half=0.5;
	private final int intialCount = 1;
	private final int noDiscount=0;
	private final double commisonPercentage = 0.02;

	private final IJourneyRepository journeyRepository;
	private final IStationRepository stationRepository;
	private final IMetroCardRepository metroCardRepository;
	
	public MetroService(IMetroCardRepository metroCardRepository, IJourneyRepository journeyRepository, IStationRepository stationRepository) {
		this.metroCardRepository = metroCardRepository;
		this.stationRepository= stationRepository;
		this.journeyRepository = journeyRepository;
	}
	
	public void checkIn(String metroCardNumber, String passengerType, String station) {
		MetroCard metroCard = metroCardRepository.findByMetroCardNumber(metroCardNumber);
		Journey journey = new Journey(metroCardNumber, station, passengerType, true);
		Journey journeyNew = journeyRepository.isReturnJourney(journey);
		
		if(isReturnJourney(journeyNew)) {
			processReturnJourney(metroCard, passengerType, station);
		}else {
			processSingleJourney(metroCard, passengerType, station, journey);
		}
	}

	private void processSingleJourney(MetroCard metroCard, String passengerType, String station, Journey journey) {
		int fare = metroCard.getBalance() - PassengerFares.getFare(passengerType);
		double commision= getCommision(metroCard,passengerType,fare);
		journeyRepository.save(journey);
		updateStationForSingleJourney(station, passengerType, commision);
		MetroCard metroCard2 = metroCardRepository.findByMetroCardNumber(metroCard.getMetroCardNumber());
		int newFare = metroCard2.getBalance() - PassengerFares.getFare(passengerType);
		updateMetroCard(metroCard, passengerType, newFare);
	}

	private void updateStationForSingleJourney(String station, String passengerType, double commision) {
		Station stationBean = stationRepository.findByStation(station);
		if(stationBean == null) {
			Map<String, Integer> passengerCount = new HashMap<>();
			passengerCount.put(passengerType, 1);
			Station stationNewBean = new Station(station, PassengerFares.getFare(passengerType)+commision, noDiscount, passengerCount);
			stationRepository.save(stationNewBean);
		}else {
			Map<String, Integer> passengerCount = stationBean.getPassengerCount();
			passengerCount.put(passengerType, passengerCount.get(passengerType)==null ? intialCount : passengerCount.get(passengerType)+intialCount);
			
			double totalRevenue = stationBean.getTotalRevenue() + PassengerFares.getFare(passengerType);
			double totalDiscount = stationBean.getTotalDiscount();
			Station stationNewBean = new Station(stationBean.getStation(), totalRevenue+commision , totalDiscount , passengerCount);
			stationRepository.save(stationNewBean);
		}
	}

	private void processReturnJourney(MetroCard metroCard, String passengerType, String station) {
		int fare=metroCard.getBalance() - (int)(PassengerFares.getFare(passengerType) * half);
		double commision= getCommision(metroCard,passengerType,fare);
		updateJourneyAsCompleted(metroCard.getMetroCardNumber(), station, passengerType);
		updateStation(station, passengerType, commision);
		MetroCard newMetroCard = metroCardRepository.findByMetroCardNumber(metroCard.getMetroCardNumber());
		int fareNew=newMetroCard.getBalance() - (int)(PassengerFares.getFare(passengerType) * half);
		updateMetroCard(metroCard, passengerType, fareNew);
	}

	private MetroCard updateMetroCard(MetroCard metroCard, String passengerType, int fare) {
		MetroCard newMetroCard = new MetroCard(metroCard.getMetroCardNumber(), fare);
		return metroCardRepository.save(newMetroCard);
	}

	private void updateStation(String station, String passengerType, double commision) {
		Station stationBean = stationRepository.findByStation(station);
		if(stationBean == null) {
			Map<String, Integer> passengerCount = new HashMap<>();
			passengerCount.put(passengerType, intialCount);
			
			Station stationNewBean = new Station(station, PassengerFares.getFare(passengerType)*half+commision, PassengerFares.getFare(passengerType)*half, passengerCount);
			stationRepository.save(stationNewBean);
		}else {
			Map<String, Integer> passengerCount = stationBean.getPassengerCount();
			passengerCount.put(passengerType, passengerCount.get(passengerType)==null ? intialCount : passengerCount.get(passengerType)+1);
			double totalRevenue = stationBean.getTotalRevenue() + PassengerFares.getFare(passengerType)*half;
			double totalDiscount = stationBean.getTotalDiscount() + PassengerFares.getFare(passengerType)*half;
			Station stationNewBean = new Station(stationBean.getStation(), totalRevenue+commision , totalDiscount , passengerCount);
			stationRepository.save(stationNewBean);
		}
	}

	private void updateJourneyAsCompleted(String metroCardNumber, String station, String passengerType) {
		Journey journeyMakingFalse = new Journey(metroCardNumber, station, passengerType, false);
		journeyRepository.save(journeyMakingFalse);
	}

	private double getCommision(MetroCard metroCard, String passengerType, int leftBalance) {
		
//		int leftBalance = metroCard.getBalance()-PassengerFares.getFare(passengerType);
		double commision = 0;
		if(leftBalance<0) {
			commision = Math.abs(leftBalance)*commisonPercentage;
			MetroCard metroCard2=new MetroCard(metroCard.getMetroCardNumber(), metroCard.getBalance()+Math.abs(leftBalance));
			metroCardRepository.save(metroCard2);
		}
		return commision;
		
	}

	private boolean isReturnJourney(Journey journeyNew) {
		return journeyNew != null && journeyNew.isReturnJourney();
	}

	public void printSummary() {
//		List<Station> allInfo = stationRepository.findAll();
		print(stationRepository.findByStation("CENTRAL"));
		print(stationRepository.findByStation("AIRPORT"));
		
	}
	
	private void print(Station station) {
		System.out.println("TOTAL_COLLECTION  "+station.getStation()+" "+(int)station.getTotalRevenue()+" "+(int)station.getTotalDiscount());
		// Sorting the map based on value (descending order) and key (alphabetical order if values are the same)
		System.out.println("PASSENGER_TYPE_SUMMARY");
		station.getPassengerCount().entrySet().stream()
		    .sorted((a, b) -> {
		        if (!b.getValue().equals(a.getValue())) {
		            return b.getValue().compareTo(a.getValue()); // Sort by count (descending)
		        }
		        return a.getKey().compareTo(b.getKey()); // Sort by key (alphabetical order)
		    })
		    .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));

	}
	
	
}
