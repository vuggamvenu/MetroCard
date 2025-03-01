package com.example.geektrust;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.example.geektrust.Commands.CommandInvoker;
import com.example.geektrust.config.ApplicationConfig;

public class Main {
	
    public static void main(String[] args) {
    	
    	//0 need change to 1 before submitting
    	if (args.length == 0){
            try {
//                List<String> file_commands = Files.readAllLines(Paths.get(args[0]));
            	List<String> file_commands = Files.readAllLines(Paths.get("C:\\Users\\Venu Vuggam\\Desktop\\Crio Coaching\\MyProjects\\commands.txt"));
                // Execute the commands
                new Main().run(file_commands);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }     
       
    }

	public void run(List<String> allLines) {
		
		ApplicationConfig config = new ApplicationConfig();
		CommandInvoker commandInvoker =  config.getCommandInvoker();
		for(String line : allLines) {
			commandInvoker.invoke(line);
		}
    		
	}
}
