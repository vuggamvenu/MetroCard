
package com.example.geektrust.Commands;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.geektrust.Entities.MetroCard;
import com.example.geektrust.service.IMetroCardService;

@ExtendWith(MockitoExtension.class)
public class BalanceCommandTest {

    @Mock
    private IMetroCardService metroCardServiceMock;

    @InjectMocks
    private BalanceCommand balanceCommand;

    @Test
    @DisplayName("invoke method of BalanceCommand should create a new MetroCard given cardNumber and balance")
    public void invoke_shouldCreateMetroCard_givenCardNumberAndBalance() {
        // Arrange
        MetroCard metroCard = new MetroCard("MC1", 600); // Assuming MetroCard constructor
        String[] tokens = {"BALANCE", "MC1", "600"};
        
        when(metroCardServiceMock.save("MC1", 600)).thenReturn(metroCard);

        // Act
        balanceCommand.invoke(tokens);

        // Assert
        verify(metroCardServiceMock, times(1)).save("MC1", 600);
    }
}
