package com.example.geektrust.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.geektrust.Entities.MetroCard;
import com.example.geektrust.repository.IMetroCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MetroCardServiceTest {

    @Mock
    private IMetroCardRepository metroRepositoryMock;

    @InjectMocks
    private MetroCardService metroCardService;

    @BeforeEach
    public void setup() {
        // Any setup can be added here if needed
    }

    @Test
    @DisplayName("balance should create and save MetroCard correctly")
    public void balance_shouldCreateAndSaveMetroCard() {
        // Arrange
        String cardNumber = "MC1";
        int balanceAmount = 500;
        MetroCard expectedMetroCard = new MetroCard(cardNumber, balanceAmount);

        when(metroRepositoryMock.save(any(MetroCard.class))).thenReturn(expectedMetroCard);

        // Act
        MetroCard result = metroCardService.balance(cardNumber, balanceAmount);

        // Assert
        assertEquals(expectedMetroCard, result);
        verify(metroRepositoryMock, times(1)).save(any(MetroCard.class));
    }

    @Test
    @DisplayName("balance should throw exception when repository fails")
    public void balance_shouldThrowException_whenRepositoryFails() {
        // Arrange
        String cardNumber = "MC1";
        int balanceAmount = 500;

        when(metroRepositoryMock.save(any(MetroCard.class))).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            metroCardService.balance(cardNumber, balanceAmount);
        });

        assertEquals("Database error", exception.getMessage());
        verify(metroRepositoryMock, times(1)).save(any(MetroCard.class));
    }

    @Test
    @DisplayName("balance should handle zero balance correctly")
    public void balance_shouldHandleZeroBalance() {
        // Arrange
        String cardNumber = "MC1";
        int balanceAmount = 0;
        MetroCard expectedMetroCard = new MetroCard(cardNumber, balanceAmount);

        when(metroRepositoryMock.save(any(MetroCard.class))).thenReturn(expectedMetroCard);

        // Act
        MetroCard result = metroCardService.balance(cardNumber, balanceAmount);

        // Assert
        assertEquals(expectedMetroCard, result);
        verify(metroRepositoryMock, times(1)).save(any(MetroCard.class));
    }

    @Test
    @DisplayName("balance should correctly save negative balance if allowed")
    public void balance_shouldHandleNegativeBalance_ifAllowed() {
        // Arrange
        String cardNumber = "MC1";
        int balanceAmount = -100;
        MetroCard expectedMetroCard = new MetroCard(cardNumber, balanceAmount);

        when(metroRepositoryMock.save(any(MetroCard.class))).thenReturn(expectedMetroCard);

        // Act
        MetroCard result = metroCardService.balance(cardNumber, balanceAmount);

        // Assert
        assertEquals(expectedMetroCard, result);
        verify(metroRepositoryMock, times(1)).save(any(MetroCard.class));
    }
}
