package com.example.geektrust.service;

import com.example.geektrust.Entities.MetroCard;
import com.example.geektrust.repository.IMetroCardRepository;

public class MetroCardService implements IMetroCardService{

	private final IMetroCardRepository metroRepository;
	
	public MetroCardService(IMetroCardRepository metroRepository) {
		this.metroRepository = metroRepository;
	}
	
	public MetroCard balance(String metroCardNumber, int balance) {
		MetroCard metroCard = new MetroCard(metroCardNumber, balance);
		return metroRepository.save(metroCard);
	}

	
}
