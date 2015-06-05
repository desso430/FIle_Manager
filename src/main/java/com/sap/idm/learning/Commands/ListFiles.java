package com.sap.idm.learning.Commands;

import java.io.File;
import java.util.List;

public class ListFiles extends  PromptOperation {

	private static final int NUMBER_OF_NECESSARY_ARGUMENTS = 1;
	
	public void listFiles(File directory) {
		if(isValidDirectory(directory)) {
			File[] files = directory.listFiles();

			if(files == null) return;
			
			for(File file : files) {
				if(file.isDirectory()) {
					System.out.println("-------- Folder: " + file.getName());
					listFiles(file);
				} else  
				   System.out.println(file.getName());
			}
		}		
	}

	@Override
	public void executeOperation(List<String> arguments) {
		boolean areArgumentsNumberCorrect = argumentsNumberCheck(NUMBER_OF_NECESSARY_ARGUMENTS, arguments.size());
		if(areArgumentsNumberCorrect) {
			String directory = arguments.get(FIRST_ARGUMENT);
			listFiles(openFile(directory));
		}
	}	
}
