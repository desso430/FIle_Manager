package com.sap.idm.learning.operations;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OpenFile extends Operation {	 

	@Override
	public void executeOperation(List<String> arguments) {		
		boolean areArgumentsEnough = areArgumentsEnough(ARGUMENTS_ONE, ARGUMENTS_TWO, arguments.size());
		if(areArgumentsEnough) {
			String pathToFile = arguments.get(ARGUMENTS_ZERO);
			File file = new File(pathToFile);		
			
			if(isValidFile(file)) {
				String encoding = getEncoding(arguments);
				readFile(pathToFile, encoding);				
			} else {
				System.out.println(FILE_NOT_FOUND_MSG);
			}	
		}
	}
	
	public void readFile(String fileName, String encoding) {	
		Charset charset = Charset.forName(encoding);
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName), charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	
	private String getEncoding(List<String> arguments) {
		int argumentsNumber = arguments.size();
		
		if(argumentsNumber == ARGUMENTS_TWO) {
			String encoding = arguments.get(ARGUMENTS_ONE);
			return encoding;
		}		
		return DEFAULT_ENCODING;
	}
}
