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

    
}
