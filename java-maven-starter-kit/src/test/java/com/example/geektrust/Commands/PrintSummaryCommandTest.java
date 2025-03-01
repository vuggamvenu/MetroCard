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
public class PrintSummaryCommandTest {

    @Mock
    private IMetroService metroServiceMock;

    @InjectMocks
    private PrintSummaryCommand printSummaryCommand;

    @Test
    @DisplayName("invoke method of PrintSummaryCommand should call printSummary on IMetroService")
    public void invoke_shouldCallPrintSummaryMethod() {
        // Arrange
        doNothing().when(metroServiceMock).printSummary();

        // Act
        printSummaryCommand.invoke(new String[]{"PRINT_SUMMARY"});

        // Assert
        verify(metroServiceMock, times(1)).printSummary();
    }
}
