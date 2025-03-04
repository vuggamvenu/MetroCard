package com.example.geektrust.service;

import com.example.geektrust.Entities.MetroCard;

public interface IMetroCardService {
	public MetroCard save(String metroCardNumber, int balance);

	public MetroCard getMetroCard(String metroCardNumber);

	public MetroCard rechargeMetroCard(String metroCardNumber, int leftBalance);

	public MetroCard updateMetroCard(String metroCardNumber, int updatedBalance);
}
