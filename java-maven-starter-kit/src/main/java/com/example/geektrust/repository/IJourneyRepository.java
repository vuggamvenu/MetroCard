package com.example.geektrust.repository;

import com.example.geektrust.Entities.Journey;

public interface IJourneyRepository {

	Journey isReturnJourney(String metroCardNumber);

	void save(Journey journey);

}
