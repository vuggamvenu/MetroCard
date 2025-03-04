package com.example.geektrust.service;

public class MetroService implements IMetroService{
	
	private final IJourneyService journeyService;
    private final IMetroCardService metroCardService;
    private final IStationService stationService;
    private final IFareCalculatorService fareCalculatorService;
    private final IReportingService reportingService;

    public MetroService(IJourneyService journeyService, IMetroCardService metroCardService,
                        IStationService stationService, IFareCalculatorService fareCalculatorService,
                        IReportingService reportingService) {
        this.journeyService = journeyService;
        this.metroCardService = metroCardService;
        this.stationService = stationService;
        this.fareCalculatorService = fareCalculatorService;
        this.reportingService = reportingService;
    }
    
    public void checkIn(String metroCardNumber, String passengerType, String station) {
        journeyService.processJourney(metroCardNumber, passengerType, station);
    }

    public void printSummary() {
        reportingService.generateReport();
    }
	
//	private final double singleJourneyFarePercentage = 1;
//	private final double half=0.5;
//	private final int intialCount = 1;
//	private final int noDiscount=0;
//	private final double commisonPercentage = 0.02;
//
//	private final IJourneyRepository journeyRepository;
//	private final IStationRepository stationRepository;
//	private final IMetroCardRepository metroCardRepository;
//	
//	public MetroService(IMetroCardRepository metroCardRepository, IJourneyRepository journeyRepository, IStationRepository stationRepository) {
//		this.metroCardRepository = metroCardRepository;
//		this.stationRepository= stationRepository;
//		this.journeyRepository = journeyRepository;
//	}
//	
//	public void checkIn(String metroCardNumber, String passengerType, String station) {
//		MetroCard metroCard = metroCardRepository.findByMetroCardNumber(metroCardNumber);
//		Journey journey = new Journey(metroCardNumber, station, passengerType, true);
//		Journey journeyNew = journeyRepository.isReturnJourney(metroCardNumber);
//		
//		if(isReturnJourney(journeyNew)) {
//			processReturnJourney(metroCard, passengerType, station);
//		}else {
//			processSingleJourney(metroCard, passengerType, station, journey);
//		}
//	}
//
//	private void processSingleJourney(MetroCard metroCard, String passengerType, String station, Journey journey) {
//		int leftBalance = findLeftBalance(metroCard, passengerType, singleJourneyFarePercentage);
//		double commision= getCommision(metroCard,passengerType,leftBalance);
//		journeyRepository.save(journey);
//		updateStationForSingleJourney(station, passengerType, commision);
//		MetroCard metroCard2 = metroCardRepository.findByMetroCardNumber(metroCard.getMetroCardNumber());
//		int newFare = metroCard2.getBalance() - PassengerFares.getFare(passengerType);
//		updateMetroCard(metroCard, passengerType, newFare);
//	}
//
//	private void updateStationForSingleJourney(String station, String passengerType, double commision) {
//		Station stationBean = stationRepository.findByStation(station);
//		if(stationBean == null) {
//			Map<String, Integer> passengerCount = new HashMap<>();
//			passengerCount.put(passengerType, 1);
//			Station stationNewBean = new Station(station, PassengerFares.getFare(passengerType)+commision, noDiscount, passengerCount);
//			stationRepository.save(stationNewBean);
//		}else {
//			Map<String, Integer> passengerCount = stationBean.getPassengerCount();
//			passengerCount.put(passengerType, passengerCount.get(passengerType)==null ? intialCount : passengerCount.get(passengerType)+intialCount);
//			
//			double totalRevenue = stationBean.getTotalRevenue() + PassengerFares.getFare(passengerType);
//			double totalDiscount = stationBean.getTotalDiscount();
//			Station stationNewBean = new Station(stationBean.getStation(), totalRevenue+commision , totalDiscount , passengerCount);
//			stationRepository.save(stationNewBean);
//		}
//	}
//
//	//for return journey
//	private void processReturnJourney(MetroCard metroCard, String passengerType, String station) {
//		int leftBalance = findLeftBalance(metroCard, passengerType, half);
//		double commision= getCommision(metroCard,passengerType,leftBalance);
//		updateJourneyAsCompleted(metroCard.getMetroCardNumber(), station, passengerType);
//		updateStation(station, passengerType, commision);
//		MetroCard newMetroCard = metroCardRepository.findByMetroCardNumber(metroCard.getMetroCardNumber());
//		int fareNew=newMetroCard.getBalance() - (int)(PassengerFares.getFare(passengerType) * half);
//		updateMetroCard(metroCard, passengerType, fareNew);
//	}
//
//	private int findLeftBalance(MetroCard metroCard, String passengerType, double percentageFare) {
//		return metroCard.getBalance() - (int)(PassengerFares.getFare(passengerType) * percentageFare);
//	}
//
//	private MetroCard updateMetroCard(MetroCard metroCard, String passengerType, int fare) {
//		MetroCard newMetroCard = new MetroCard(metroCard.getMetroCardNumber(), fare);
//		return metroCardRepository.save(newMetroCard);
//	}
//
//	private Station updateStation(String station, String passengerType, double commision) {
//		Station stationBean = stationRepository.findByStation(station);
//		if(stationBean == null) {
//			return createNewStation(station,passengerType,commision);
//		}else {
//			return updateExisitionStation(station, passengerType, commision, stationBean);
//		}
//	}
//
//	private Station updateExisitionStation(String station, String passengerType, double commision, Station stationBean) {
//		Map<String ,Integer> passengerCount = createExistionPassengerCount(stationBean, passengerType);
//		double totalRevenue = stationBean.getTotalRevenue() + calculateRevenue(passengerType, commision);
//		double totalDiscount = stationBean.getTotalDiscount() + calculateDiscount(passengerType);
//		return saveStation(station, totalRevenue, totalDiscount, passengerCount);
//	}
//
//	private Map<String, Integer> createExistionPassengerCount(Station stationBean, String passengerType) {
//		Map<String, Integer> passengerCount = stationBean.getPassengerCount();
//		passengerCount.put(passengerType, passengerCount.getOrDefault(passengerType,0)+1);
//		return passengerCount;
//	}
//
//	private Station createNewStation(String station, String passengerType, double commision) {
//		Map<String, Integer> passengerCount = createNewPassengerCount(passengerType);
//		double revenue = calculateRevenue(passengerType, commision);
//		double discount = calculateDiscount(passengerType);
//		return saveStation(station, revenue, discount, passengerCount);
//	}
//
//	private Station saveStation(String station, double revenue, double discount, Map<String, Integer> passengerCount) {
//		Station stationNewBean = new Station(station, revenue , discount, passengerCount);
//		return stationRepository.save(stationNewBean);
//	}
//
//	private double calculateDiscount(String passengerType) {
//		return PassengerFares.getFare(passengerType)*half;
//	}
//
//	private double calculateRevenue(String passengerType, double commision) {
//		return PassengerFares.getFare(passengerType)*half+commision;
//	}
//
//	private Map<String, Integer> createNewPassengerCount(String passengerType) {
//		Map<String, Integer> passengerCount = new HashMap<>();
//		passengerCount.put(passengerType, intialCount);
//		return passengerCount;
//	}
//
//	private void updateJourneyAsCompleted(String metroCardNumber, String station, String passengerType) {
//		Journey journeyMakingFalse = new Journey(metroCardNumber, station, passengerType, false);
//		journeyRepository.save(journeyMakingFalse);
//	}
//
//	private double getCommision(MetroCard metroCard, String passengerType, int leftBalance) {
//		
//		double commision = 0;
//		if(leftBalance<0) {
//			commision = Math.abs(leftBalance)*commisonPercentage;
//			MetroCard metroCardSaved = rechargeMetroCard(metroCard, leftBalance);
//		}
//		return commision;
//		
//	}
//
//	private MetroCard rechargeMetroCard(MetroCard metroCard, int leftBalance) {
//		MetroCard metroCard2=new MetroCard(metroCard.getMetroCardNumber(), metroCard.getBalance()+Math.abs(leftBalance));
//		return metroCardRepository.save(metroCard2);
//	}
//
//	private boolean isReturnJourney(Journey journeyNew) {
//		return journeyNew != null && journeyNew.isReturnJourney();
//	}
//
//	public void printSummary() {
//		print(stationRepository.findByStation("CENTRAL"));
//		print(stationRepository.findByStation("AIRPORT"));
//		
//	}
//	
//	private void print(Station station) {
//		System.out.println("TOTAL_COLLECTION  "+station.getStation()+" "+(int)station.getTotalRevenue()+" "+(int)station.getTotalDiscount());
//		// Sorting the map based on value (descending order) and key (alphabetical order if values are the same)
//		System.out.println("PASSENGER_TYPE_SUMMARY");
//		station.getPassengerCount().entrySet().stream()
//		    .sorted((a, b) -> {
//		        if (!b.getValue().equals(a.getValue())) {
//		            return b.getValue().compareTo(a.getValue()); // Sort by count (descending)
//		        }
//		        return a.getKey().compareTo(b.getKey()); // Sort by key (alphabetical order)
//		    })
//		    .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
//
//	}
	
}
