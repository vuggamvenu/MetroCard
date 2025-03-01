package com.example.geektrust.Commands;

import com.example.geektrust.service.IMetroService;

public class PrintSummaryCommand implements ICommand{

	private final IMetroService metroService;
	
	public PrintSummaryCommand(IMetroService metroService) {
		this.metroService = metroService;
	}
	
	@Override
	public void invoke(String[] tokens) {
		metroService.printSummary();
	}

}
