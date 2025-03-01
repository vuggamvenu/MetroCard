package com.example.geektrust.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.geektrust.Entities.Journey;
import com.example.geektrust.Entities.MetroCard;
import com.example.geektrust.Entities.Station;
import com.example.geektrust.repository.IJourneyRepository;
import com.example.geektrust.repository.IMetroCardRepository;
import com.example.geektrust.repository.IStationRepository;

@ExtendWith(MockitoExtension.class)
public class MetroServiceTest {

    @Mock
    private IMetroCardRepository metroCardRepositoryMock;
    
    @Mock
    private IJourneyRepository journeyRepositoryMock;
    
    @Mock
    private IStationRepository stationRepositoryMock;
    
    @InjectMocks
    private MetroService metroService;

    @BeforeEach
    public void setup() {
        // Any setup if needed
    }

    

    @Test
    @DisplayName("checkIn should process a return journey with discount")
    public void checkIn_shouldProcessReturnJourneyWithDiscount() {
        // Arrange
        String metroCardNumber = "MC1";
        String passengerType = "ADULT";
        String station = "CENTRAL";
        MetroCard metroCard = new MetroCard(metroCardNumber, 100);
        Journey previousJourney = new Journey(metroCardNumber, "AIRPORT", passengerType, true);
        when(metroCardRepositoryMock.findByMetroCardNumber(metroCardNumber)).thenReturn(metroCard);
        when(journeyRepositoryMock.isReturnJourney(any(Journey.class))).thenReturn(previousJourney);
        
        // Act
        metroService.checkIn(metroCardNumber, passengerType, station);
        
        // Assert
        verify(journeyRepositoryMock, times(1)).save(any(Journey.class));
        verify(metroCardRepositoryMock, times(1)).save(any(MetroCard.class));
    }

    

    @Test
    @DisplayName("printSummary should fetch station details")
    public void printSummary_shouldFetchStationDetails() {
        // Arrange
        Station centralStation = new Station("CENTRAL", 200, 50, new HashMap<>());
        Station airportStation = new Station("AIRPORT", 300, 100, new HashMap<>());
        when(stationRepositoryMock.findByStation("CENTRAL")).thenReturn(centralStation);
        when(stationRepositoryMock.findByStation("AIRPORT")).thenReturn(airportStation);
        
        // Act
        metroService.printSummary();
        
        // Assert
        verify(stationRepositoryMock, times(1)).findByStation("CENTRAL");
        verify(stationRepositoryMock, times(1)).findByStation("AIRPORT");
    }

    

    @Test
    @DisplayName("hasSufficientBalance should return true for enough balance")
    public void hasSufficientBalance_shouldReturnTrue() {
        // Arrange
        MetroCard metroCard = new MetroCard("MC1", 100);
        when(metroCardRepositoryMock.findByMetroCardNumber("MC1")).thenReturn(metroCard);
        
        // Act & Assert
        assertDoesNotThrow(() -> metroService.checkIn("MC1", "ADULT", "CENTRAL"));
    }

    
}
