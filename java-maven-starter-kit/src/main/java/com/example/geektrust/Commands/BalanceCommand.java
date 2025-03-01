package com.example.geektrust.Commands;

import com.example.geektrust.service.IMetroCardService;

public class BalanceCommand implements ICommand{
	
	private final IMetroCardService metroCardService;
	
	public BalanceCommand(IMetroCardService metroCardService) {
		this.metroCardService= metroCardService;
	}

	@Override
	public void invoke(String[] tokens) {
		String metroCardNumber = tokens[1];
		String balance = tokens[2];
		metroCardService.balance(metroCardNumber,Integer.parseInt(balance));
	}

}
