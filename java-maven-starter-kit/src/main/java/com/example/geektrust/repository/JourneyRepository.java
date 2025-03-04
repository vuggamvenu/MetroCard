package com.example.geektrust.repository;

import java.util.HashMap;
import java.util.Map;

import com.example.geektrust.Entities.Journey;

public class JourneyRepository implements IJourneyRepository{

	private final Map<String, Journey> storage = new HashMap<>();
	
	@Override
	public Journey isReturnJourney(String metroCardNumber) {
		return storage.get(metroCardNumber);
	}

	@Override
	public void save(Journey journey) {
		storage.put(journey.getMetroCardNumber(), journey);
	}

}
