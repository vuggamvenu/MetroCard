package com.example.geektrust.Commands;

import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {

	 private final Map<String, ICommand> commands = new HashMap<>();
	 private final String delimiter = " ";
	 private final int indexOfCommand = 0;

	    public void register(String command,ICommand commandObject){
	        commands.putIfAbsent(command, commandObject);
	    }
	    
	    public void invoke(String line){
	        String[] tokens=line.split(delimiter);
	        ICommand command = commands.get(tokens[indexOfCommand]);
	        if(command==null){
	            throw new RuntimeException("INVALID_COMMAND");
	        }
	        command.invoke(tokens);
	    }
}
