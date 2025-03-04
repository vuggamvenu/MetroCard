package com.example.geektrust.service;

import com.example.geektrust.Entities.Station;

public interface IStationService {

	Station updateStationForReturnOrSingleJourney(String station, String passengerType, double commision, boolean isReturnJourney);

}
