package com.example.geektrust.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.geektrust.Entities.MetroCard;

public class MetroCardRepository implements IMetroCardRepository{
	
	private final Map<String, MetroCard> storage = new HashMap<>();

	@Override
	public MetroCard save(MetroCard metroCard) {
		storage.put(metroCard.getMetroCardNumber(), metroCard);
		return metroCard;
	}

	@Override
	public MetroCard findByMetroCardNumber(String metroCardNumber) {
		return storage.get(metroCardNumber);
	}

	@Override
	public List<MetroCard> findAll() {
		return new ArrayList<>(storage.values());
	}

}
