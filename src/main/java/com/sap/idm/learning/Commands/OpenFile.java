package com.sap.idm.learning.Commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OpenFile extends PromptOperation {
	 
	private static final int NUMBER_OF_NECESSARY_ARGUMENTS = 1;
	
	public void readFile(String fileName) {	
		String encoding = getEncoding(fileName);
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

	public static String getEncoding(String fileName)  {
		InputStream fis = null;
		InputStreamReader isr = null;
		
		try {
			fis = new FileInputStream(fileName);
			isr = new InputStreamReader(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				isr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String encoding = isr.getEncoding();
		return encoding;
	}
	
	@Override
	public void executeOperation(List<String> arguments) {
		boolean areArgumentsNumberCorrect = argumentsNumberCheck(NUMBER_OF_NECESSARY_ARGUMENTS, arguments.size());
		if(areArgumentsNumberCorrect) {
			String pathToFile = arguments.get(FIRST_ARGUMENT);
			File file = new File(pathToFile);
			
			 if(isValidFile(file)) {
			    readFile(pathToFile);
			 } else {
				 System.out.println(FILE_NOT_FOUND_MSG);
			 }
		}
	}
}
