package com.example.geektrust.repository;

import java.util.List;

import com.example.geektrust.Entities.MetroCard;

public interface IMetroCardRepository {
	MetroCard save(MetroCard metroCard);

	MetroCard findByMetroCardNumber(String metroCardNumber);

	List<MetroCard> findAll();
}
