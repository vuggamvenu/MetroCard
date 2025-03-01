package com.example.geektrust.Commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommandInvokerTest {

    private CommandInvoker commandInvoker;

    @Mock
    private ICommand mockCommand;

    @BeforeEach
    public void setup() {
        commandInvoker = new CommandInvoker();
    }

    @Test
    @DisplayName("invoke should call the correct registered command with tokens")
    public void invoke_shouldCallCorrectCommand_whenRegistered() {
        // Arrange
        String commandLine = "CHECK_IN MC1 ADULT CENTRAL";
        commandInvoker.register("CHECK_IN", mockCommand);

        // Act
        commandInvoker.invoke(commandLine);

        // Assert
        verify(mockCommand, times(1)).invoke(commandLine.split(" "));
    }

    @Test
    @DisplayName("invoke should throw RuntimeException for unregistered commands")
    public void invoke_shouldThrowException_whenCommandNotRegistered() {
        // Arrange
        String invalidCommandLine = "INVALID_COMMAND MC1";

        // Act & Assert
        assertThrows(RuntimeException.class, () -> commandInvoker.invoke(invalidCommandLine));
    }
}