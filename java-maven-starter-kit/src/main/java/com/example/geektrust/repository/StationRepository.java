package com.example.geektrust.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.geektrust.Entities.Station;

public class StationRepository implements IStationRepository{

	private final Map<String, Station> storage = new HashMap<>();
	
	@Override
	public Station findByStation(String station) {
		return storage.get(station);
	}

	@Override
	public void save(Station station) {
		storage.put(station.getStation(), station);
	}

	@Override
	public List<Station> findAll() {
		return new ArrayList<>(storage.values());
	}

}
