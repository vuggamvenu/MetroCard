package com.example.geektrust.service;

import com.example.geektrust.Entities.Station;
import com.example.geektrust.repository.IStationRepository;

public class ReportingService implements IReportingService{
	
	private final IStationRepository stationRepository;

	public ReportingService(IStationRepository stationRepository) {
		this.stationRepository = stationRepository;
	}

	@Override
	public void generateReport() {
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
