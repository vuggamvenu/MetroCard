package com.example.geektrust.repository;

import java.util.List;

import com.example.geektrust.Entities.Station;

public interface IStationRepository {

	Station findByStation(String station);

	Station save(Station stationNewBean);

	List<Station> findAll();

}
