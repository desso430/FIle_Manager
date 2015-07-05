package com.sap.idm.learning.operations;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Help extends Operation {

	private static final String HELP_INFO_FILE = "Commands.txt";

	@Override
	public void executeOperation(List<String> arguments) {
		help();
	}
	
	public void help() {		
		Charset charset = Charset.forName(DEFAULT_ENCODING);
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(HELP_INFO_FILE), charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		    
		    System.out.println("\n");
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
}
