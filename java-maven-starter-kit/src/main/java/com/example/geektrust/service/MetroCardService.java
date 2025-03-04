package com.example.geektrust.service;

import com.example.geektrust.Entities.MetroCard;
import com.example.geektrust.repository.IMetroCardRepository;
import com.example.geektrust.util.PassengerFares;

public class MetroCardService implements IMetroCardService{

	private final IMetroCardRepository metroCardRepository;
	
	public MetroCardService(IMetroCardRepository metroCardRepository) {
		this.metroCardRepository = metroCardRepository;
	}
	
	public MetroCard save(String metroCardNumber, int balance) {
		MetroCard metroCard = new MetroCard(metroCardNumber, balance);
		return metroCardRepository.save(metroCard);
	}

	@Override
	public MetroCard getMetroCard(String metroCardNumber) {
		return metroCardRepository.findByMetroCardNumber(metroCardNumber);
	}

	@Override
	public MetroCard rechargeMetroCard(String metroCardNumber, int leftBalance) {
		MetroCard metroCard =  metroCardRepository.findByMetroCardNumber(metroCardNumber);
		MetroCard updateMetroCard=new MetroCard(metroCard.getMetroCardNumber(), metroCard.getBalance()+Math.abs(leftBalance));
		return metroCardRepository.save(updateMetroCard);
	}

	@Override
	public MetroCard updateMetroCard(String metroCardNumber, int updatedBalance) {
		MetroCard metroCard = new MetroCard(metroCardNumber, updatedBalance);
		return metroCardRepository.save(metroCard);
	}

	
}
