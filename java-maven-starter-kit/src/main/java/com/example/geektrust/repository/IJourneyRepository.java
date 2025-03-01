package com.example.geektrust.repository;

import com.example.geektrust.Entities.Journey;

public interface IJourneyRepository {

	Journey isReturnJourney(Journey journey);

	void save(Journey journey);

}
