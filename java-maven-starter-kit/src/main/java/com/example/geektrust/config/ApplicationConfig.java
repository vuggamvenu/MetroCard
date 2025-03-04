package com.example.geektrust.config;

import com.example.geektrust.Commands.BalanceCommand;
import com.example.geektrust.Commands.CheckInCommand;
import com.example.geektrust.Commands.CommandInvoker;
import com.example.geektrust.Commands.ICommand;
import com.example.geektrust.Commands.PrintSummaryCommand;
import com.example.geektrust.repository.IJourneyRepository;
import com.example.geektrust.repository.IMetroCardRepository;
import com.example.geektrust.repository.IStationRepository;
import com.example.geektrust.repository.JourneyRepository;
import com.example.geektrust.repository.MetroCardRepository;
import com.example.geektrust.repository.StationRepository;
import com.example.geektrust.service.FareCalculatorService;
import com.example.geektrust.service.IFareCalculatorService;
import com.example.geektrust.service.IJourneyService;
import com.example.geektrust.service.IMetroCardService;
import com.example.geektrust.service.IMetroService;
import com.example.geektrust.service.IReportingService;
import com.example.geektrust.service.IStationService;
import com.example.geektrust.service.JourneyService;
import com.example.geektrust.service.MetroCardService;
import com.example.geektrust.service.MetroService;
import com.example.geektrust.service.ReportingService;
import com.example.geektrust.service.StationService;

public class ApplicationConfig {
	
	// Initialize Repositories
    private final IMetroCardRepository metroRepository = new MetroCardRepository();
    private final IStationRepository stationRepository = new StationRepository();
    private final IJourneyRepository journeyRepository = new JourneyRepository();
    
    

    // Initialize Services
    
    
    
    private final IReportingService reportService = new ReportingService(stationRepository);
    private final IMetroCardService metroCardService = new MetroCardService(metroRepository);
    private final IFareCalculatorService fareCalculatorService = new FareCalculatorService(metroCardService);
    private final IStationService stationService = new StationService(stationRepository,fareCalculatorService);
    private final IJourneyService journeyService = new JourneyService(journeyRepository, metroCardService, fareCalculatorService, stationService);
    IMetroService metroService = new MetroService(journeyService, metroCardService, stationService, fareCalculatorService, reportService);
//    private final IMetroService metroService = new MetroService(metroRepository, journeyRepository, stationRepository);
    
    private final ICommand balanceCommand = new BalanceCommand(metroCardService);
    private final ICommand checkInCommand = new CheckInCommand(metroService);
    private final ICommand printSummaryCommand = new PrintSummaryCommand(metroService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker() {
        final String COMMAND_BALANCE = "BALANCE";
        final String COMMAND_CHECK_IN = "CHECK_IN";
        final String COMMAND_PRINT_SUMMARY = "PRINT_SUMMARY";

        commandInvoker.register(COMMAND_BALANCE, balanceCommand);
        commandInvoker.register(COMMAND_CHECK_IN, checkInCommand);
        commandInvoker.register(COMMAND_PRINT_SUMMARY, printSummaryCommand);
        return commandInvoker;
    }
}
