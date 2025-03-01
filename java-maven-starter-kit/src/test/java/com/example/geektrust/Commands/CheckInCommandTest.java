package com.example.geektrust.Commands;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;

import com.example.geektrust.service.IMetroService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CheckInCommandTest {

    @Mock
    private IMetroService metroServiceMock;

    @InjectMocks
    private CheckInCommand checkInCommand;

    @Test
    @DisplayName("invoke method of CheckInCommand should call checkIn on IMetroService with correct parameters")
    public void invoke_shouldCallCheckInMethod_givenCorrectParameters() {
        // Arrange
        String[] tokens = {"CHECK_IN", "MC1", "ADULT", "CENTRAL"};

        // Mocking void method behavior
        doNothing().when(metroServiceMock).checkIn("MC1", "ADULT", "CENTRAL");

        // Act
        checkInCommand.invoke(tokens);

        // Assert
        verify(metroServiceMock, times(1)).checkIn("MC1", "ADULT", "CENTRAL");
    }
}
