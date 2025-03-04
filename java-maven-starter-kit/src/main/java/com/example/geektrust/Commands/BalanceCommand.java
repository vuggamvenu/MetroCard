package com.example.geektrust.Commands;

import com.example.geektrust.service.IMetroCardService;

public class BalanceCommand implements ICommand{
	
	private final IMetroCardService metroCardService;
	
	public BalanceCommand(IMetroCardService metroCardService) {
		this.metroCardService= metroCardService;
	}

	@Override
	public void invoke(String[] tokens) {
		int cardIndex = 1;
		int balanceIndex = 2;
		String metroCardNumber = tokens[cardIndex];
		String balance = tokens[balanceIndex];
		metroCardService.save(metroCardNumber,Integer.parseInt(balance));
	}

}
