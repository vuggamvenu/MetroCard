package com.example.geektrust.service;

import com.example.geektrust.Entities.MetroCard;

public interface IMetroCardService {
	public MetroCard balance(String metroCardNumber, int balance);
}
