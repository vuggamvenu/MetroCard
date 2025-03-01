package com.example.geektrust.Commands;

import com.example.geektrust.service.IMetroService;

public class CheckInCommand implements ICommand{

	private final IMetroService metroService;
	
	public CheckInCommand(IMetroService metroService) {
		this.metroService = metroService;
	}
	
	@Override
	public void invoke(String[] tokens) {
		String metroCardNumber = tokens[1];
		String passengerType = tokens[2];
		String station = tokens[3];
		metroService.checkIn(metroCardNumber,passengerType,station);
	}

}
